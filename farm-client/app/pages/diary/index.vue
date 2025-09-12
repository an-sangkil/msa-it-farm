<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            농장 일지
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            농장의 일상적인 활동과 관리 기록을 작성하고 조회할 수 있습니다
          </p>
        </div>
        <UButton 
          to="/diary/form"
          size="lg"
          icon="i-heroicons-plus"
        >
          일지 작성
        </UButton>
      </div>

      <!-- Search Filters -->
      <UCard class="mb-6">
        <template #header>
          <h2 class="text-lg font-semibold text-gray-900 dark:text-white">검색 필터</h2>
        </template>

        <UForm :state="searchForm" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
            <UFormGroup label="작성일 (시작)">
              <UInput 
                v-model="searchForm.startDate"
                type="date"
              />
            </UFormGroup>

            <UFormGroup label="작성일 (종료)">
              <UInput 
                v-model="searchForm.endDate"
                type="date"
              />
            </UFormGroup>

            <UFormGroup label="카테고리">
              <USelect 
                v-model="searchForm.category"
                :options="categoryOptions"
                placeholder="전체"
              />
            </UFormGroup>

            <UFormGroup label="검색어">
              <UInput 
                v-model="searchForm.keyword"
                placeholder="제목 또는 내용"
              />
            </UFormGroup>
          </div>

          <div class="flex justify-end space-x-2">
            <UButton variant="outline" @click="resetSearch">
              초기화
            </UButton>
            <UButton @click="searchDiary" icon="i-heroicons-magnifying-glass">
              검색
            </UButton>
          </div>
        </UForm>
      </UCard>

      <!-- Diary List -->
      <div class="space-y-4">
        <!-- 로딩 상태 -->
        <div v-if="loading" class="text-center py-12">
          <UIcon name="i-heroicons-arrow-path" class="w-8 h-8 animate-spin mx-auto mb-4" />
          <p class="text-gray-600 dark:text-gray-400">일지를 불러오는 중...</p>
        </div>

        <!-- 일지 목록 -->
        <div v-else-if="diaryList.length > 0" class="space-y-4">
          <UCard 
            v-for="diary in diaryList" 
            :key="diary.id"
            class="hover:shadow-md transition-shadow cursor-pointer"
            @click="viewDetail(diary)"
          >
            <div class="flex items-start justify-between">
              <div class="flex-1">
                <div class="flex items-center space-x-3 mb-3">
                  <UBadge 
                    :color="getCategoryColor(diary.category)" 
                    variant="soft"
                  >
                    {{ getCategoryLabel(diary.category) }}
                  </UBadge>
                  <span class="text-sm text-gray-500 dark:text-gray-400">
                    {{ formatDate(diary.createdAt) }}
                  </span>
                  <span class="text-sm text-gray-500 dark:text-gray-400">
                    작성자: {{ diary.author }}
                  </span>
                </div>
                
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                  {{ diary.title }}
                </h3>
                
                <p class="text-gray-600 dark:text-gray-400 line-clamp-2">
                  {{ diary.summary }}
                </p>

                <div class="mt-3 flex items-center space-x-4 text-sm text-gray-500 dark:text-gray-400">
                  <div class="flex items-center space-x-1">
                    <UIcon name="i-heroicons-calendar-days" class="w-4 h-4" />
                    <span>{{ diary.workDate }}</span>
                  </div>
                  <div class="flex items-center space-x-1">
                    <UIcon name="i-heroicons-clock" class="w-4 h-4" />
                    <span>{{ formatTime(diary.createdAt) }}</span>
                  </div>
                  <div v-if="diary.weather" class="flex items-center space-x-1">
                    <UIcon name="i-heroicons-sun" class="w-4 h-4" />
                    <span>{{ diary.weather }}</span>
                  </div>
                </div>
              </div>

              <div class="flex-shrink-0 ml-4">
                <UDropdown :items="getActionItems(diary)">
                  <UButton variant="ghost" size="sm" icon="i-heroicons-ellipsis-vertical" />
                </UDropdown>
              </div>
            </div>
          </UCard>
        </div>

        <!-- 빈 상태 -->
        <div v-else class="text-center py-12">
          <UIcon name="i-heroicons-document-text" class="w-12 h-12 text-gray-400 mx-auto mb-4" />
          <p class="text-gray-600 dark:text-gray-400 mb-4">작성된 일지가 없습니다</p>
          <UButton to="/diary/form">첫 번째 일지 작성하기</UButton>
        </div>

        <!-- Pagination -->
        <div v-if="diaryList.length > 0" class="flex justify-center mt-8">
          <UPagination 
            v-model="pagination.currentPage"
            :page-count="pagination.pageSize"
            :total="pagination.total"
            :max="5"
            @update:model-value="onPageChange"
          />
        </div>
      </div>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 페이지 메타데이터
useSeoMeta({
  title: '농장 일지 - MSA IT Farm',
  description: '농장 일지 관리 시스템'
})

// 타입 정의
interface DiaryItem {
  id: number
  title: string
  summary: string
  category: string
  workDate: string
  createdAt: string
  author: string
  weather?: string
}

interface SearchForm {
  startDate: string
  endDate: string
  category: string
  keyword: string
}

// 반응형 데이터
const loading = ref(false)
const diaryList = ref<DiaryItem[]>([])

const searchForm = reactive<SearchForm>({
  startDate: '',
  endDate: '',
  category: '',
  keyword: ''
})

const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

// 옵션 데이터
const categoryOptions = [
  { label: '전체', value: '' },
  { label: '사료 급여', value: 'feed' },
  { label: '건강 관리', value: 'health' },
  { label: '청소', value: 'cleaning' },
  { label: '번식 관리', value: 'breeding' },
  { label: '기타', value: 'etc' }
]

// 유틸리티 함수
const getCategoryLabel = (category: string): string => {
  const categoryMap: Record<string, string> = {
    feed: '사료 급여',
    health: '건강 관리',
    cleaning: '청소',
    breeding: '번식 관리',
    etc: '기타'
  }
  return categoryMap[category] || category
}

const getCategoryColor = (category: string): string => {
  const colorMap: Record<string, string> = {
    feed: 'green',
    health: 'red',
    cleaning: 'blue',
    breeding: 'purple',
    etc: 'gray'
  }
  return colorMap[category] || 'gray'
}

const formatDate = (dateString: string): string => {
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}

const formatTime = (dateString: string): string => {
  const date = new Date(dateString)
  return date.toLocaleTimeString('ko-KR', {
    hour: '2-digit',
    minute: '2-digit'
  })
}

const getActionItems = (diary: DiaryItem) => [
  [
    {
      label: '상세 보기',
      icon: 'i-heroicons-eye',
      click: () => viewDetail(diary)
    },
    {
      label: '수정',
      icon: 'i-heroicons-pencil',
      click: () => editDiary(diary)
    }
  ],
  [
    {
      label: '삭제',
      icon: 'i-heroicons-trash',
      click: () => deleteDiary(diary)
    }
  ]
]

// 이벤트 핸들러
const searchDiary = async () => {
  loading.value = true
  try {
    await loadDiaryList(1)
  } finally {
    loading.value = false
  }
}

const resetSearch = () => {
  Object.assign(searchForm, {
    startDate: '',
    endDate: '',
    category: '',
    keyword: ''
  })
  searchDiary()
}

const onPageChange = (page: number) => {
  loadDiaryList(page)
}

const viewDetail = (diary: DiaryItem) => {
  navigateTo(`/diary/${diary.id}`)
}

const editDiary = (diary: DiaryItem) => {
  navigateTo(`/diary/form/${diary.id}`)
}

const deleteDiary = async (diary: DiaryItem) => {
  // TODO: 삭제 확인 다이얼로그 표시
  if (confirm(`"${diary.title}" 일지를 삭제하시겠습니까?`)) {
    try {
      // TODO: 실제 API 호출
      console.log('일지 삭제:', diary.id)
      await loadDiaryList(pagination.currentPage)
    } catch (error) {
      console.error('일지 삭제 실패:', error)
    }
  }
}

// 데이터 로딩
const loadDiaryList = async (page: number = 1) => {
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    // const response = await $fetch('/api/diary/list', {
    //   query: {
    //     page: page - 1,
    //     size: pagination.pageSize,
    //     ...searchForm
    //   }
    // })
    
    // 임시 더미 데이터
    const dummyData: DiaryItem[] = Array.from({ length: 8 }, (_, index) => ({
      id: index + 1,
      title: `농장 일지 #${index + 1} - ${['사료 급여', '건강 체크', '청소 작업', '번식 관리'][index % 4]}`,
      summary: `오늘은 ${['사료 급여를 완료했습니다', '소들의 건강 상태를 점검했습니다', '축사 청소를 실시했습니다', '번식 관리 업무를 진행했습니다'][index % 4]}. 특별한 이상은 발견되지 않았으며 모든 업무가 원활하게 진행되었습니다.`,
      category: ['feed', 'health', 'cleaning', 'breeding'][index % 4],
      workDate: `2024-09-${(12 - index).toString().padStart(2, '0')}`,
      createdAt: `2024-09-${(12 - index).toString().padStart(2, '0')}T${(9 + index % 8).toString().padStart(2, '0')}:30:00`,
      author: '농장 관리자',
      weather: index % 2 === 0 ? ['맑음', '흐림', '비'][index % 3] : undefined
    }))
    
    diaryList.value = dummyData
    pagination.total = 24
    pagination.currentPage = page
    
  } catch (error) {
    console.error('일지 목록 로딩 실패:', error)
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  // 기본적으로 최근 7일간의 일지를 보여주기 위해 날짜 설정
  const today = new Date()
  const weekAgo = new Date(today.getTime() - 7 * 24 * 60 * 60 * 1000)
  
  searchForm.startDate = weekAgo.toISOString().split('T')[0]
  searchForm.endDate = today.toISOString().split('T')[0]
  
  loadDiaryList()
})
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>