<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            소 상세 정보
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            개체식별번호: {{ formatEntityId(cattle?.entityIdentificationNumber || '') }}
          </p>
        </div>
        <div class="flex space-x-2">
          <UButton variant="outline" to="/cattle" icon="i-heroicons-arrow-left">
            목록으로
          </UButton>
          <UButton 
            :to="`/cattle/form/${route.params.entityManagementNumber}/${route.params.entityIdentificationNumber}`"
            icon="i-heroicons-pencil"
          >
            수정
          </UButton>
        </div>
      </div>

      <div v-if="loading" class="text-center py-12">
        <UIcon name="i-heroicons-arrow-path" class="w-8 h-8 animate-spin mx-auto mb-4" />
        <p class="text-gray-600 dark:text-gray-400">소 정보를 불러오는 중...</p>
      </div>

      <div v-else-if="!cattle" class="text-center py-12">
        <UIcon name="i-heroicons-exclamation-triangle" class="w-12 h-12 text-yellow-500 mx-auto mb-4" />
        <p class="text-gray-600 dark:text-gray-400">해당 소의 정보를 찾을 수 없습니다.</p>
      </div>

      <div v-else class="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <!-- 기본 정보 카드 -->
        <div class="lg:col-span-2">
          <UCard>
            <template #header>
              <div class="flex items-center justify-between">
                <h2 class="text-xl font-semibold text-gray-900 dark:text-white">기본 정보</h2>
                <UBadge 
                  :color="cattle.gender === 'MALE' ? 'blue' : 'pink'"
                  variant="soft"
                  size="lg"
                >
                  {{ cattle.gender === 'MALE' ? '수컷' : '암컷' }}
                </UBadge>
              </div>
            </template>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  개체식별번호
                </label>
                <p class="text-lg font-mono text-gray-900 dark:text-white">
                  {{ formatEntityId(cattle.entityIdentificationNumber) }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  관리번호
                </label>
                <p class="text-lg font-mono text-gray-900 dark:text-white">
                  {{ formatManagementId(cattle.entityManagementNumber) }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  생년월일
                </label>
                <p class="text-lg text-gray-900 dark:text-white">
                  {{ cattle.birthDay || '-' }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  개월수
                </label>
                <p class="text-lg text-gray-900 dark:text-white">
                  {{ cattle.numberOfMonth ? `${cattle.numberOfMonth}개월` : '-' }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  사육방
                </label>
                <p class="text-lg text-gray-900 dark:text-white">
                  {{ cattle.roomNumber || '-' }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  모 개체식별번호
                </label>
                <p class="text-lg font-mono text-gray-900 dark:text-white">
                  {{ cattle.parentMomNo || '-' }}
                </p>
              </div>
            </div>
          </UCard>
        </div>

        <!-- 상태 정보 카드 -->
        <div>
          <UCard class="mb-6">
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">상태 정보</h2>
            </template>

            <div class="space-y-4">
              <div class="flex items-center justify-between">
                <span class="text-sm font-medium text-gray-700 dark:text-gray-300">건강 상태</span>
                <UBadge color="green" variant="soft">양호</UBadge>
              </div>

              <div class="flex items-center justify-between">
                <span class="text-sm font-medium text-gray-700 dark:text-gray-300">최근 관찰</span>
                <span class="text-sm text-gray-600 dark:text-gray-400">2시간 전</span>
              </div>

              <div class="flex items-center justify-between">
                <span class="text-sm font-medium text-gray-700 dark:text-gray-300">체중 (예상)</span>
                <span class="text-sm text-gray-900 dark:text-white">{{ estimatedWeight }}kg</span>
              </div>
            </div>
          </UCard>

          <!-- 빠른 액션 카드 -->
          <UCard>
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">빠른 액션</h2>
            </template>

            <div class="space-y-3">
              <UButton block variant="outline" icon="i-heroicons-eye">
                관찰 기록 추가
              </UButton>
              <UButton block variant="outline" icon="i-heroicons-document-text">
                일지 작성
              </UButton>
              <UButton block variant="outline" icon="i-heroicons-heart" color="red">
                건강 체크
              </UButton>
            </div>
          </UCard>
        </div>

        <!-- 번식 정보 카드 -->
        <div class="lg:col-span-3">
          <UCard>
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">번식 정보</h2>
            </template>

            <div class="grid grid-cols-1 md:grid-cols-4 gap-6">
              <div class="text-center">
                <div class="text-3xl font-bold text-blue-600 dark:text-blue-400 mb-2">
                  {{ (cattle.calvesCount || 1) - 1 }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">분만횟수</div>
              </div>

              <div class="text-center">
                <div class="text-3xl font-bold text-green-600 dark:text-green-400 mb-2">
                  {{ cattle.inseminationCount || 0 }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">수정횟수</div>
              </div>

              <div class="text-center">
                <div class="text-lg font-semibold text-purple-600 dark:text-purple-400 mb-2">
                  {{ cattle.expectedDateChildbirth || '-' }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">분만예정일</div>
              </div>

              <div class="text-center">
                <div class="text-lg font-semibold text-orange-600 dark:text-orange-400 mb-2">
                  {{ daysUntilBirth || '-' }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">분만까지 남은 일수</div>
              </div>
            </div>
          </UCard>
        </div>

        <!-- 최근 활동 내역 -->
        <div class="lg:col-span-3">
          <UCard>
            <template #header>
              <div class="flex items-center justify-between">
                <h2 class="text-xl font-semibold text-gray-900 dark:text-white">최근 활동 내역</h2>
                <UButton variant="ghost" size="xs">전체 보기</UButton>
              </div>
            </template>

            <div class="space-y-4">
              <div v-for="activity in recentActivities" :key="activity.id" 
                   class="flex items-start space-x-4 p-4 bg-gray-50 dark:bg-gray-800 rounded-lg">
                <div class="flex-shrink-0">
                  <UIcon :name="activity.icon" :class="activity.iconColor" class="w-5 h-5" />
                </div>
                <div class="flex-1">
                  <p class="text-sm font-medium text-gray-900 dark:text-white">
                    {{ activity.title }}
                  </p>
                  <p class="text-xs text-gray-600 dark:text-gray-400">
                    {{ activity.description }}
                  </p>
                  <p class="text-xs text-gray-500 dark:text-gray-400 mt-1">
                    {{ activity.time }}
                  </p>
                </div>
              </div>
            </div>
          </UCard>
        </div>
      </div>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 페이지 메타데이터
useSeoMeta({
  title: '소 상세 정보 - MSA IT Farm'
})

// 타입 정의
interface CattleDetail {
  entityIdentificationNumber: string
  entityManagementNumber: string
  gender: 'MALE' | 'FEMALE'
  birthDay?: string
  numberOfMonth?: number
  roomNumber?: string
  calvesCount?: number
  inseminationCount?: number
  expectedDateChildbirth?: string
  parentMomNo?: string
  notes?: string
}

interface Activity {
  id: number
  icon: string
  iconColor: string
  title: string
  description: string
  time: string
}

// 라우터
const route = useRoute()

// 반응형 데이터
const loading = ref(true)
const cattle = ref<CattleDetail | null>(null)

const recentActivities = ref<Activity[]>([
  {
    id: 1,
    icon: 'i-heroicons-heart',
    iconColor: 'text-red-500',
    title: '건강 체크 완료',
    description: '정상적인 상태를 확인했습니다.',
    time: '2시간 전'
  },
  {
    id: 2,
    icon: 'i-heroicons-document-text',
    iconColor: 'text-blue-500',
    title: '사료 급여 기록',
    description: '오전 사료 급여를 완료했습니다.',
    time: '4시간 전'
  },
  {
    id: 3,
    icon: 'i-heroicons-eye',
    iconColor: 'text-green-500',
    title: '관찰 기록 추가',
    description: '활발한 움직임을 보이고 있습니다.',
    time: '6시간 전'
  }
])

// 계산된 속성
const estimatedWeight = computed(() => {
  if (!cattle.value?.numberOfMonth) return null
  // 간단한 체중 추정 공식 (실제로는 더 복잡한 공식 사용)
  return Math.round(cattle.value.numberOfMonth * 18 + 50)
})

const daysUntilBirth = computed(() => {
  if (!cattle.value?.expectedDateChildbirth) return null
  
  const today = new Date()
  const birthDate = new Date(cattle.value.expectedDateChildbirth)
  const diffTime = birthDate.getTime() - today.getTime()
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
  
  if (diffDays < 0) return '출산 완료'
  if (diffDays === 0) return '오늘'
  return `${diffDays}일`
})

// 유틸리티 함수
const formatEntityId = (id: string) => {
  if (!id || id.length < 15) return id
  return `${id.substring(0, 3)}-${id.substring(3, 7)}-${id.substring(7, 11)}-${id.substring(11)}`
}

const formatManagementId = (id: string) => {
  if (!id || id.length < 8) return id
  return `${id.substring(0, 4)}-${id.substring(4, 8)}`
}

// 데이터 로딩
const loadCattleDetail = async () => {
  loading.value = true
  
  try {
    const { entityManagementNumber, entityIdentificationNumber } = route.params
    
    // TODO: 실제 API 호출로 대체
    console.log('소 상세 정보 로드:', { entityManagementNumber, entityIdentificationNumber })
    
    // 임시 더미 데이터
    cattle.value = {
      entityIdentificationNumber: entityIdentificationNumber as string,
      entityManagementNumber: entityManagementNumber as string,
      gender: 'FEMALE',
      birthDay: '2022-03-15',
      numberOfMonth: 24,
      roomNumber: 'A-1',
      calvesCount: 3,
      inseminationCount: 2,
      expectedDateChildbirth: '2024-12-15',
      parentMomNo: '110123456789001',
      notes: '건강한 상태입니다.'
    }
    
    await new Promise(resolve => setTimeout(resolve, 1000)) // 로딩 시뮬레이션
    
  } catch (error) {
    console.error('소 상세 정보 로드 실패:', error)
    cattle.value = null
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  loadCattleDetail()
})
</script>

<style scoped>
/* 커스텀 스타일이 필요한 경우 */
</style>