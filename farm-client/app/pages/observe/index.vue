<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            관찰 기록
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            소들의 건강 상태와 행동을 관찰하고 기록합니다
          </p>
        </div>
        <UButton 
          to="/observe/form"
          size="lg"
          icon="i-heroicons-plus"
        >
          관찰 기록 추가
        </UButton>
      </div>

      <!-- Search Filters -->
      <UCard class="mb-6">
        <template #header>
          <h2 class="text-lg font-semibold text-gray-900 dark:text-white">검색 필터</h2>
        </template>

        <UForm :state="searchForm" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
            <UFormGroup label="개체식별번호">
              <UInput 
                v-model="searchForm.entityIdentificationNumber"
                placeholder="개체식별번호 입력"
              />
            </UFormGroup>

            <UFormGroup label="관찰일 (시작)">
              <UInput 
                v-model="searchForm.startDate"
                type="date"
              />
            </UFormGroup>

            <UFormGroup label="관찰일 (종료)">
              <UInput 
                v-model="searchForm.endDate"
                type="date"
              />
            </UFormGroup>

            <UFormGroup label="건강 상태">
              <USelect 
                v-model="searchForm.healthStatus"
                :options="healthStatusOptions"
                placeholder="전체"
              />
            </UFormGroup>
          </div>

          <div class="flex justify-end space-x-2">
            <UButton variant="outline" @click="resetSearch">
              초기화
            </UButton>
            <UButton @click="searchObservations" icon="i-heroicons-magnifying-glass">
              검색
            </UButton>
          </div>
        </UForm>
      </UCard>

      <!-- Observations List -->
      <div class="space-y-4">
        <!-- 로딩 상태 -->
        <div v-if="loading" class="text-center py-12">
          <UIcon name="i-heroicons-arrow-path" class="w-8 h-8 animate-spin mx-auto mb-4" />
          <p class="text-gray-600 dark:text-gray-400">관찰 기록을 불러오는 중...</p>
        </div>

        <!-- 관찰 기록 목록 -->
        <div v-else-if="observationList.length > 0" class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-6">
          <UCard 
            v-for="observation in observationList" 
            :key="observation.id"
            class="hover:shadow-lg transition-shadow cursor-pointer"
            @click="viewDetail(observation)"
          >
            <template #header>
              <div class="flex items-center justify-between">
                <div>
                  <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                    {{ formatEntityId(observation.entityIdentificationNumber) }}
                  </h3>
                  <p class="text-sm text-gray-500 dark:text-gray-400">
                    {{ formatDate(observation.observationDate) }}
                  </p>
                </div>
                <UBadge 
                  :color="getHealthStatusColor(observation.healthStatus)" 
                  variant="soft"
                >
                  {{ getHealthStatusLabel(observation.healthStatus) }}
                </UBadge>
              </div>
            </template>

            <div class="space-y-4">
              <!-- 건강 지표 -->
              <div class="grid grid-cols-2 gap-4 text-sm">
                <div>
                  <span class="text-gray-500 dark:text-gray-400">체중</span>
                  <p class="font-semibold">{{ observation.weight ? `${observation.weight}kg` : '-' }}</p>
                </div>
                <div>
                  <span class="text-gray-500 dark:text-gray-400">체온</span>
                  <p class="font-semibold">{{ observation.temperature ? `${observation.temperature}°C` : '-' }}</p>
                </div>
                <div>
                  <span class="text-gray-500 dark:text-gray-400">식욕</span>
                  <p class="font-semibold">{{ getAppetiteLabel(observation.appetite) }}</p>
                </div>
                <div>
                  <span class="text-gray-500 dark:text-gray-400">활동성</span>
                  <p class="font-semibold">{{ getActivityLabel(observation.activity) }}</p>
                </div>
              </div>

              <!-- 관찰 내용 -->
              <div>
                <p class="text-sm text-gray-600 dark:text-gray-400 line-clamp-3">
                  {{ observation.observations }}
                </p>
              </div>

              <!-- 메타 정보 -->
              <div class="flex items-center justify-between text-xs text-gray-500 dark:text-gray-400 pt-2 border-t border-gray-200 dark:border-gray-700">
                <span>관찰자: {{ observation.observer }}</span>
                <span>{{ formatTime(observation.createdAt) }}</span>
              </div>
            </div>

            <!-- 액션 버튼 -->
            <template #footer>
              <div class="flex justify-end space-x-2">
                <UButton 
                  variant="ghost" 
                  size="xs" 
                  @click.stop="editObservation(observation)"
                >
                  수정
                </UButton>
                <UButton 
                  variant="ghost" 
                  size="xs" 
                  color="red"
                  @click.stop="deleteObservation(observation)"
                >
                  삭제
                </UButton>
              </div>
            </template>
          </UCard>
        </div>

        <!-- 빈 상태 -->
        <div v-else class="text-center py-12">
          <UIcon name="i-heroicons-eye" class="w-12 h-12 text-gray-400 mx-auto mb-4" />
          <p class="text-gray-600 dark:text-gray-400 mb-4">관찰 기록이 없습니다</p>
          <UButton to="/observe/form">첫 번째 관찰 기록 추가하기</UButton>
        </div>

        <!-- Pagination -->
        <div v-if="observationList.length > 0" class="flex justify-center mt-8">
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
  title: '관찰 기록 - MSA IT Farm',
  description: '농장 소 관찰 기록 관리 시스템'
})

// 타입 정의
interface ObservationItem {
  id: number
  entityIdentificationNumber: string
  observationDate: string
  healthStatus: string
  weight?: number
  temperature?: number
  appetite: string
  activity: string
  observations: string
  observer: string
  createdAt: string
}

interface SearchForm {
  entityIdentificationNumber: string
  startDate: string
  endDate: string
  healthStatus: string
}

// 반응형 데이터
const loading = ref(false)
const observationList = ref<ObservationItem[]>([])

const searchForm = reactive<SearchForm>({
  entityIdentificationNumber: '',
  startDate: '',
  endDate: '',
  healthStatus: ''
})

const pagination = reactive({
  currentPage: 1,
  pageSize: 12,
  total: 0
})

// 옵션 데이터
const healthStatusOptions = [
  { label: '전체', value: '' },
  { label: '양호', value: 'good' },
  { label: '주의', value: 'caution' },
  { label: '이상', value: 'abnormal' },
  { label: '치료중', value: 'treatment' }
]

// 유틸리티 함수
const formatEntityId = (id: string) => {
  if (!id || id.length < 15) return id
  return `${id.substring(0, 3)}-${id.substring(3, 7)}-${id.substring(7, 11)}-${id.substring(11)}`
}

const formatDate = (dateString: string): string => {
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'short',
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

const getHealthStatusLabel = (status: string): string => {
  const statusMap: Record<string, string> = {
    good: '양호',
    caution: '주의',
    abnormal: '이상',
    treatment: '치료중'
  }
  return statusMap[status] || status
}

const getHealthStatusColor = (status: string): string => {
  const colorMap: Record<string, string> = {
    good: 'green',
    caution: 'yellow',
    abnormal: 'red',
    treatment: 'blue'
  }
  return colorMap[status] || 'gray'
}

const getAppetiteLabel = (appetite: string): string => {
  const appetiteMap: Record<string, string> = {
    good: '양호',
    normal: '보통',
    poor: '부진',
    none: '거부'
  }
  return appetiteMap[appetite] || appetite
}

const getActivityLabel = (activity: string): string => {
  const activityMap: Record<string, string> = {
    active: '활발',
    normal: '보통',
    sluggish: '둔함',
    inactive: '비활성'
  }
  return activityMap[activity] || activity
}

// 이벤트 핸들러
const searchObservations = async () => {
  loading.value = true
  try {
    await loadObservationList(1)
  } finally {
    loading.value = false
  }
}

const resetSearch = () => {
  Object.assign(searchForm, {
    entityIdentificationNumber: '',
    startDate: '',
    endDate: '',
    healthStatus: ''
  })
  searchObservations()
}

const onPageChange = (page: number) => {
  loadObservationList(page)
}

const viewDetail = (observation: ObservationItem) => {
  navigateTo(`/observe/${observation.id}`)
}

const editObservation = (observation: ObservationItem) => {
  navigateTo(`/observe/form/${observation.id}`)
}

const deleteObservation = async (observation: ObservationItem) => {
  if (confirm('이 관찰 기록을 삭제하시겠습니까?')) {
    try {
      // TODO: 실제 API 호출
      console.log('관찰 기록 삭제:', observation.id)
      await loadObservationList(pagination.currentPage)
    } catch (error) {
      console.error('관찰 기록 삭제 실패:', error)
    }
  }
}

// 데이터 로딩
const loadObservationList = async (page: number = 1) => {
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    const dummyData: ObservationItem[] = Array.from({ length: 12 }, (_, index) => ({
      id: index + 1,
      entityIdentificationNumber: `11012345678901${index.toString().padStart(2, '0')}`,
      observationDate: `2024-09-${(12 - index).toString().padStart(2, '0')}`,
      healthStatus: ['good', 'caution', 'abnormal', 'treatment'][index % 4],
      weight: index % 2 === 0 ? 450 + index * 10 : undefined,
      temperature: index % 3 === 0 ? 38.5 + (Math.random() - 0.5) : undefined,
      appetite: ['good', 'normal', 'poor', 'none'][index % 4],
      activity: ['active', 'normal', 'sluggish', 'inactive'][index % 4],
      observations: `${index + 1}번 소의 관찰 기록입니다. ${['정상적인 상태를 보이고 있습니다.', '약간의 주의가 필요한 상태입니다.', '이상 증상이 관찰되어 추가 관찰이 필요합니다.', '치료가 진행 중이며 회복되고 있는 상태입니다.'][index % 4]} 식욕과 활동성을 지속적으로 모니터링하겠습니다.`,
      observer: '농장 관리자',
      createdAt: `2024-09-${(12 - index).toString().padStart(2, '0')}T${(9 + index % 8).toString().padStart(2, '0')}:30:00`
    }))
    
    observationList.value = dummyData
    pagination.total = 48
    pagination.currentPage = page
    
  } catch (error) {
    console.error('관찰 기록 로딩 실패:', error)
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  // 기본적으로 최근 7일간의 관찰 기록을 보여주기 위해 날짜 설정
  const today = new Date()
  const weekAgo = new Date(today.getTime() - 7 * 24 * 60 * 60 * 1000)
  
  searchForm.startDate = weekAgo.toISOString().split('T')[0]
  searchForm.endDate = today.toISOString().split('T')[0]
  
  loadObservationList()
})
</script>

<style scoped>
.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>