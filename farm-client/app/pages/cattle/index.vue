<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            소 관리
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            농장의 소를 관리하고 정보를 조회할 수 있습니다
          </p>
        </div>
        <UButton 
          to="/cattle/form"
          size="lg"
          icon="i-heroicons-plus"
        >
          소 등록
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

            <UFormGroup label="암수구분">
              <USelect 
                v-model="searchForm.gender"
                :options="genderOptions"
                placeholder="선택하세요"
              />
            </UFormGroup>

            <UFormGroup label="사육방">
              <UInput 
                v-model="searchForm.roomNumber"
                placeholder="사육방 번호"
              />
            </UFormGroup>

            <UFormGroup label="개월수">
              <UInput 
                v-model="searchForm.numberOfMonth"
                type="number"
                placeholder="개월수"
              />
            </UFormGroup>
          </div>

          <div class="flex justify-end space-x-2">
            <UButton variant="outline" @click="resetSearch">
              초기화
            </UButton>
            <UButton @click="searchCattle" icon="i-heroicons-magnifying-glass">
              검색
            </UButton>
          </div>
        </UForm>
      </UCard>

      <!-- Cattle List -->
      <UCard>
        <template #header>
          <div class="flex items-center justify-between">
            <h2 class="text-lg font-semibold text-gray-900 dark:text-white">
              소 목록 ({{ pagination.total }}마리)
            </h2>
          </div>
        </template>

        <div class="overflow-x-auto">
          <UTable 
            :columns="columns" 
            :rows="cattleList"
            :loading="loading"
            @select="onRowClick"
          >
            <!-- 개체식별번호 포맷팅 -->
            <template #entityIdentificationNumber-data="{ row }">
              <UButton 
                variant="link"
                @click="viewDetail(row)"
                class="text-primary-600 hover:text-primary-500"
              >
                {{ formatEntityId(row.entityIdentificationNumber) }}
              </UButton>
            </template>

            <!-- 관리번호 포맷팅 -->
            <template #entityManagementNumber-data="{ row }">
              <UButton 
                variant="link"
                @click="viewDetail(row)"
                class="text-primary-600 hover:text-primary-500"
              >
                {{ formatManagementId(row.entityManagementNumber) }}
              </UButton>
            </template>

            <!-- 암수구분 -->
            <template #gender-data="{ row }">
              <UBadge 
                :color="row.gender === 'MALE' ? 'blue' : 'pink'"
                variant="soft"
              >
                {{ row.gender === 'MALE' ? '수컷' : '암컷' }}
              </UBadge>
            </template>

            <!-- 분만횟수 -->
            <template #calvesCount-data="{ row }">
              {{ (row.calvesCount || 1) - 1 }}
            </template>

            <!-- 빈 값 처리 -->
            <template #roomNumber-data="{ row }">
              {{ row.roomNumber || '-' }}
            </template>

            <template #expectedDateChildbirth-data="{ row }">
              {{ row.expectedDateChildbirth || '-' }}
            </template>

            <template #parentMomNo-data="{ row }">
              {{ row.parentMomNo || '-' }}
            </template>
          </UTable>
        </div>

        <!-- Pagination -->
        <template #footer>
          <div class="flex justify-center">
            <UPagination 
              v-model="pagination.currentPage"
              :page-count="pagination.pageSize"
              :total="pagination.total"
              :max="5"
              @update:model-value="onPageChange"
            />
          </div>
        </template>
      </UCard>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 페이지 메타데이터
useSeoMeta({
  title: '소 관리 - MSA IT Farm',
  description: '농장 소 관리 및 조회 시스템'
})

// 타입 정의
interface CattleItem {
  entityIdentificationNumber: string
  entityManagementNumber: string
  roomNumber?: string
  calvesCount?: number
  inseminationCount?: number
  expectedDateChildbirth?: string
  gender: 'MALE' | 'FEMALE'
  birthDay?: string
  numberOfMonth?: number
  parentMomNo?: string
}

interface SearchForm {
  entityIdentificationNumber: string
  gender: string
  roomNumber: string
  numberOfMonth: string
}

// 반응형 데이터
const loading = ref(false)
const cattleList = ref<CattleItem[]>([])

const searchForm = reactive<SearchForm>({
  entityIdentificationNumber: '',
  gender: '',
  roomNumber: '',
  numberOfMonth: ''
})

const pagination = reactive({
  currentPage: 1,
  pageSize: 20,
  total: 0
})

// 옵션 데이터
const genderOptions = [
  { label: '전체', value: '' },
  { label: '수컷', value: 'MALE' },
  { label: '암컷', value: 'FEMALE' }
]

// 테이블 컬럼 정의
const columns = [
  { key: 'entityIdentificationNumber', label: '개체식별번호' },
  { key: 'entityManagementNumber', label: '관리번호' },
  { key: 'roomNumber', label: '사육방' },
  { key: 'calvesCount', label: '분만횟수' },
  { key: 'inseminationCount', label: '수정횟수' },
  { key: 'expectedDateChildbirth', label: '분만예정일' },
  { key: 'gender', label: '암수구분' },
  { key: 'birthDay', label: '생년월일' },
  { key: 'numberOfMonth', label: '개월수' },
  { key: 'parentMomNo', label: '모 개체식별번호' }
]

// 유틸리티 함수
const formatEntityId = (id: string) => {
  if (!id || id.length < 15) return id
  return `${id.substring(0, 3)}-${id.substring(3, 7)}-${id.substring(7, 11)}-${id.substring(11)}`
}

const formatManagementId = (id: string) => {
  if (!id || id.length < 8) return id
  return `${id.substring(0, 4)}-${id.substring(4, 8)}`
}

// 이벤트 핸들러
const searchCattle = async () => {
  loading.value = true
  try {
    await loadCattleList(1)
  } finally {
    loading.value = false
  }
}

const resetSearch = () => {
  Object.assign(searchForm, {
    entityIdentificationNumber: '',
    gender: '',
    roomNumber: '',
    numberOfMonth: ''
  })
  searchCattle()
}

const onPageChange = (page: number) => {
  loadCattleList(page)
}

const onRowClick = (row: CattleItem) => {
  viewDetail(row)
}

const viewDetail = (cattle: CattleItem) => {
  navigateTo(`/cattle/${cattle.entityManagementNumber}/${cattle.entityIdentificationNumber}`)
}

// 데이터 로딩
const loadCattleList = async (page: number = 1) => {
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    // const response = await $fetch('/api/cattle/list', {
    //   query: {
    //     page: page - 1,
    //     size: pagination.pageSize,
    //     ...searchForm
    //   }
    // })
    
    // 임시 더미 데이터
    const dummyData = Array.from({ length: 10 }, (_, index) => ({
      entityIdentificationNumber: `11012345678901${index.toString().padStart(2, '0')}`,
      entityManagementNumber: `20240001000${index + 1}`,
      roomNumber: index % 2 === 0 ? `A-${index + 1}` : undefined,
      calvesCount: Math.floor(Math.random() * 5) + 1,
      inseminationCount: Math.floor(Math.random() * 3),
      expectedDateChildbirth: index % 3 === 0 ? '2024-12-15' : undefined,
      gender: index % 2 === 0 ? 'FEMALE' as const : 'MALE' as const,
      birthDay: `2022-${(index % 12 + 1).toString().padStart(2, '0')}-15`,
      numberOfMonth: 24 + index,
      parentMomNo: index % 2 === 0 ? `11012345678900${index}` : undefined
    }))
    
    cattleList.value = dummyData
    pagination.total = 127
    pagination.currentPage = page
    
  } catch (error) {
    console.error('소 목록 로딩 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  loadCattleList()
})
</script>