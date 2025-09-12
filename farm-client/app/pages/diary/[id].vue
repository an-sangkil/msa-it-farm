<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            일지 상세보기
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            {{ diary?.workDate }} 작업 내용
          </p>
        </div>
        <div class="flex space-x-2">
          <UButton variant="outline" to="/diary" icon="i-heroicons-arrow-left">
            목록으로
          </UButton>
          <UButton 
            v-if="diary"
            :to="`/diary/form/${diary.id}`"
            icon="i-heroicons-pencil"
          >
            수정
          </UButton>
        </div>
      </div>

      <div v-if="loading" class="text-center py-12">
        <UIcon name="i-heroicons-arrow-path" class="w-8 h-8 animate-spin mx-auto mb-4" />
        <p class="text-gray-600 dark:text-gray-400">일지를 불러오는 중...</p>
      </div>

      <div v-else-if="!diary" class="text-center py-12">
        <UIcon name="i-heroicons-exclamation-triangle" class="w-12 h-12 text-yellow-500 mx-auto mb-4" />
        <p class="text-gray-600 dark:text-gray-400">해당 일지를 찾을 수 없습니다.</p>
      </div>

      <div v-else class="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <!-- 메인 콘텐츠 -->
        <div class="lg:col-span-2 space-y-6">
          <!-- 기본 정보 -->
          <UCard>
            <template #header>
              <div class="flex items-center justify-between">
                <h2 class="text-xl font-semibold text-gray-900 dark:text-white">{{ diary.title }}</h2>
                <UBadge 
                  :color="getCategoryColor(diary.category)" 
                  variant="soft"
                  size="lg"
                >
                  {{ getCategoryLabel(diary.category) }}
                </UBadge>
              </div>
            </template>

            <div class="prose dark:prose-invert max-w-none">
              <div class="mb-6">
                <h3 class="text-lg font-semibold mb-2">작업 요약</h3>
                <p class="text-gray-700 dark:text-gray-300">{{ diary.summary }}</p>
              </div>

              <div class="mb-6">
                <h3 class="text-lg font-semibold mb-2">상세 내용</h3>
                <div class="text-gray-700 dark:text-gray-300 whitespace-pre-line">
                  {{ diary.content }}
                </div>
              </div>

              <div v-if="diary.notes" class="mb-6">
                <h3 class="text-lg font-semibold mb-2">특이사항</h3>
                <div class="text-gray-700 dark:text-gray-300 whitespace-pre-line bg-yellow-50 dark:bg-yellow-900/20 p-4 rounded-lg">
                  {{ diary.notes }}
                </div>
              </div>
            </div>
          </UCard>

          <!-- 작업 시간 정보 -->
          <UCard v-if="diary.startTime && diary.endTime">
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">작업 시간</h2>
            </template>

            <div class="grid grid-cols-1 md:grid-cols-3 gap-6 text-center">
              <div>
                <div class="text-2xl font-bold text-green-600 dark:text-green-400 mb-2">
                  {{ diary.startTime }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">작업 시작</div>
              </div>

              <div>
                <div class="text-2xl font-bold text-red-600 dark:text-red-400 mb-2">
                  {{ diary.endTime }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">작업 종료</div>
              </div>

              <div>
                <div class="text-2xl font-bold text-blue-600 dark:text-blue-400 mb-2">
                  {{ workDuration }}
                </div>
                <div class="text-sm text-gray-600 dark:text-gray-400">총 작업 시간</div>
              </div>
            </div>
          </UCard>
        </div>

        <!-- 사이드바 정보 -->
        <div class="space-y-6">
          <!-- 작업 정보 -->
          <UCard>
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">작업 정보</h2>
            </template>

            <div class="space-y-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  작업일
                </label>
                <p class="text-gray-900 dark:text-white font-semibold">
                  {{ formatDate(diary.workDate) }}
                </p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  작성자
                </label>
                <p class="text-gray-900 dark:text-white">{{ diary.author }}</p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  작성일시
                </label>
                <p class="text-gray-900 dark:text-white text-sm">
                  {{ formatDateTime(diary.createdAt) }}
                </p>
              </div>

              <div v-if="diary.updatedAt && diary.updatedAt !== diary.createdAt">
                <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                  수정일시
                </label>
                <p class="text-gray-900 dark:text-white text-sm">
                  {{ formatDateTime(diary.updatedAt) }}
                </p>
              </div>
            </div>
          </UCard>

          <!-- 날씨 정보 -->
          <UCard v-if="diary.weather || diary.temperature || diary.humidity">
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">날씨 정보</h2>
            </template>

            <div class="space-y-4">
              <div v-if="diary.weather" class="flex items-center space-x-3">
                <UIcon :name="getWeatherIcon(diary.weather)" class="w-6 h-6 text-blue-500" />
                <span class="text-gray-900 dark:text-white">{{ getWeatherLabel(diary.weather) }}</span>
              </div>

              <div v-if="diary.temperature" class="flex items-center justify-between">
                <span class="text-sm font-medium text-gray-700 dark:text-gray-300">온도</span>
                <span class="text-gray-900 dark:text-white">{{ diary.temperature }}°C</span>
              </div>

              <div v-if="diary.humidity" class="flex items-center justify-between">
                <span class="text-sm font-medium text-gray-700 dark:text-gray-300">습도</span>
                <span class="text-gray-900 dark:text-white">{{ diary.humidity }}%</span>
              </div>
            </div>
          </UCard>

          <!-- 관련 작업 -->
          <UCard>
            <template #header>
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">관련 작업</h2>
            </template>

            <div class="space-y-3">
              <UButton block variant="outline" icon="i-heroicons-building-storefront">
                관련 소 정보 보기
              </UButton>
              <UButton block variant="outline" icon="i-heroicons-eye">
                관찰 기록 보기
              </UButton>
              <UButton block variant="outline" icon="i-heroicons-document-duplicate">
                유사 일지 보기
              </UButton>
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
  title: '일지 상세보기 - MSA IT Farm'
})

// 타입 정의
interface DiaryDetail {
  id: number
  title: string
  summary: string
  content: string
  category: string
  workDate: string
  weather?: string
  temperature?: number
  humidity?: number
  startTime?: string
  endTime?: string
  notes?: string
  author: string
  createdAt: string
  updatedAt?: string
}

// 라우터
const route = useRoute()

// 반응형 데이터
const loading = ref(true)
const diary = ref<DiaryDetail | null>(null)

// 계산된 속성
const workDuration = computed(() => {
  if (!diary.value?.startTime || !diary.value?.endTime) return null
  
  const start = new Date(`2000-01-01 ${diary.value.startTime}`)
  const end = new Date(`2000-01-01 ${diary.value.endTime}`)
  
  if (end <= start) return null
  
  const diffMs = end.getTime() - start.getTime()
  const hours = Math.floor(diffMs / (1000 * 60 * 60))
  const minutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60))
  
  return `${hours}시간 ${minutes}분`
})

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

const getWeatherLabel = (weather: string): string => {
  const weatherMap: Record<string, string> = {
    sunny: '맑음',
    cloudy: '흐림',
    rainy: '비',
    snowy: '눈',
    foggy: '안개'
  }
  return weatherMap[weather] || weather
}

const getWeatherIcon = (weather: string): string => {
  const iconMap: Record<string, string> = {
    sunny: 'i-heroicons-sun',
    cloudy: 'i-heroicons-cloud',
    rainy: 'i-heroicons-cloud-arrow-down',
    snowy: 'i-heroicons-sparkles',
    foggy: 'i-heroicons-eye-slash'
  }
  return iconMap[weather] || 'i-heroicons-sun'
}

const formatDate = (dateString: string): string => {
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    weekday: 'long'
  })
}

const formatDateTime = (dateString: string): string => {
  const date = new Date(dateString)
  return date.toLocaleString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

// 데이터 로딩
const loadDiaryDetail = async () => {
  loading.value = true
  
  try {
    const diaryId = route.params.id
    
    // TODO: 실제 API 호출로 대체
    console.log('일지 상세 정보 로드:', diaryId)
    
    // 임시 더미 데이터
    diary.value = {
      id: Number(diaryId),
      title: '농장 일지 #1 - 사료 급여',
      summary: '오전 사료 급여를 완료했습니다',
      content: `오전 8시부터 모든 축사에 사료를 급여했습니다. 

소들의 식욕이 왕성하고 건강한 상태를 확인했습니다. A동에서 F동까지 순차적으로 진행했으며, 다음과 같은 작업을 수행했습니다:

1. A동 (성우 20마리) - 건초 50kg, 배합사료 30kg
2. B동 (육우 25마리) - 건초 60kg, 배합사료 40kg  
3. C동 (송아지 15마리) - 송아지 전용사료 25kg
4. D동 (임신우 18마리) - 임신우 전용사료 45kg
5. E동 (비육우 30마리) - 고단백 사료 80kg
6. F동 (격리 중인 소 3마리) - 치료용 사료 5kg

모든 소들이 정상적으로 사료를 섭취하였으며, 특별한 이상 증상은 발견되지 않았습니다.`,
      category: 'feed',
      workDate: '2024-09-12',
      weather: 'sunny',
      temperature: 25,
      humidity: 60,
      startTime: '08:00',
      endTime: '10:30',
      notes: `특이사항:
- C동 송아지 1마리가 약간 식욕이 떨어져 보임 (관찰 필요)
- E동 급수기 점검 필요 (수압이 약함)
- 전체적으로 사료 소비량이 평소보다 10% 증가`,
      author: '농장 관리자',
      createdAt: '2024-09-12T10:45:00',
      updatedAt: '2024-09-12T11:30:00'
    }
    
    await new Promise(resolve => setTimeout(resolve, 1000)) // 로딩 시뮬레이션
    
  } catch (error) {
    console.error('일지 상세 정보 로드 실패:', error)
    diary.value = null
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  loadDiaryDetail()
})
</script>

<style scoped>
.prose {
  max-width: none;
}

.prose h3 {
  margin-top: 0;
  margin-bottom: 0.5rem;
}
</style>