<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
          Dashboard
        </h1>
        <p class="text-gray-600 dark:text-gray-400">
          MSA IT Farm 농장 관리 시스템에 오신 것을 환영합니다
        </p>
      </div>

      <!-- Stats Cards -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <UCard>
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-600 dark:text-gray-400">전체 소</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ stats.totalCattle }}</p>
            </div>
            <UIcon name="i-heroicons-building-storefront" class="w-8 h-8 text-blue-500" />
          </div>
        </UCard>

        <UCard>
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-600 dark:text-gray-400">오늘 작성한 일지</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ stats.todayDiaries }}</p>
            </div>
            <UIcon name="i-heroicons-document-text" class="w-8 h-8 text-green-500" />
          </div>
        </UCard>

        <UCard>
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-600 dark:text-gray-400">관찰 기록</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ stats.observations }}</p>
            </div>
            <UIcon name="i-heroicons-eye" class="w-8 h-8 text-purple-500" />
          </div>
        </UCard>
      </div>

      <!-- Quick Actions -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
        <!-- Quick Navigation -->
        <UCard>
          <template #header>
            <div class="flex items-center justify-between">
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">빠른 이동</h2>
            </div>
          </template>

          <div class="grid grid-cols-2 gap-4">
            <UButton 
              to="/cattle"
              variant="outline"
              size="lg"
              class="flex flex-col items-center p-6 h-auto"
            >
              <UIcon name="i-heroicons-building-storefront" class="w-8 h-8 mb-2 text-blue-500" />
              <span>소 관리</span>
            </UButton>

            <UButton 
              to="/diary"
              variant="outline"
              size="lg"
              class="flex flex-col items-center p-6 h-auto"
            >
              <UIcon name="i-heroicons-document-text" class="w-8 h-8 mb-2 text-green-500" />
              <span>일지 관리</span>
            </UButton>

            <UButton 
              to="/observe"
              variant="outline"
              size="lg"
              class="flex flex-col items-center p-6 h-auto"
            >
              <UIcon name="i-heroicons-eye" class="w-8 h-8 mb-2 text-purple-500" />
              <span>관찰 기록</span>
            </UButton>

            <UButton 
              variant="outline"
              size="lg"
              class="flex flex-col items-center p-6 h-auto"
              disabled
            >
              <UIcon name="i-heroicons-chart-bar" class="w-8 h-8 mb-2 text-gray-400" />
              <span>통계 (준비중)</span>
            </UButton>
          </div>
        </UCard>

        <!-- Recent Activities -->
        <UCard>
          <template #header>
            <div class="flex items-center justify-between">
              <h2 class="text-xl font-semibold text-gray-900 dark:text-white">최근 활동</h2>
              <UButton variant="ghost" size="xs">
                전체 보기
              </UButton>
            </div>
          </template>

          <div class="space-y-4">
            <div v-for="activity in recentActivities" :key="activity.id" 
                 class="flex items-start space-x-3">
              <div class="flex-shrink-0">
                <UIcon :name="activity.icon" class="w-5 h-5 text-gray-400" />
              </div>
              <div class="flex-1">
                <p class="text-sm text-gray-900 dark:text-white">{{ activity.title }}</p>
                <p class="text-xs text-gray-500 dark:text-gray-400">{{ activity.time }}</p>
              </div>
            </div>
          </div>
        </UCard>
      </div>

      <!-- System Info -->
      <UCard>
        <template #header>
          <h2 class="text-xl font-semibold text-gray-900 dark:text-white">시스템 정보</h2>
        </template>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <h3 class="font-medium text-gray-900 dark:text-white mb-2">현재 시간</h3>
            <p class="text-2xl font-mono text-gray-600 dark:text-gray-300">{{ currentTime }}</p>
          </div>
          <div>
            <h3 class="font-medium text-gray-900 dark:text-white mb-2">시스템 상태</h3>
            <div class="flex items-center space-x-2">
              <div class="w-3 h-3 bg-green-500 rounded-full"></div>
              <span class="text-green-600 dark:text-green-400">정상 운영 중</span>
            </div>
          </div>
        </div>
      </UCard>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 페이지 메타데이터
useSeoMeta({
  title: 'Dashboard - MSA IT Farm',
  description: '농장 관리 시스템 대시보드'
})

// 반응형 데이터
const currentTime = ref('')
const stats = ref({
  totalCattle: 0,
  todayDiaries: 0,
  observations: 0
})

const recentActivities = ref([
  {
    id: 1,
    icon: 'i-heroicons-plus-circle',
    title: '새로운 소가 등록되었습니다',
    time: '2시간 전'
  },
  {
    id: 2,
    icon: 'i-heroicons-document-text',
    title: '일지가 작성되었습니다',
    time: '3시간 전'
  },
  {
    id: 3,
    icon: 'i-heroicons-eye',
    title: '관찰 기록이 업데이트되었습니다',
    time: '5시간 전'
  }
])

// 시간 업데이트 함수
const updateTime = () => {
  const now = new Date()
  currentTime.value = now.toLocaleString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  })
}

// 통계 데이터 로드 함수
const loadStats = async () => {
  try {
    // TODO: 실제 API 호출로 대체
    stats.value = {
      totalCattle: 127,
      todayDiaries: 8,
      observations: 45
    }
  } catch (error) {
    console.error('통계 데이터 로드 실패:', error)
  }
}

// 컴포넌트 마운트 시 실행
onMounted(() => {
  updateTime()
  loadStats()
  
  // 시간을 매초 업데이트
  const timeInterval = setInterval(updateTime, 1000)
  
  // 컴포넌트 언마운트 시 인터벌 정리
  onBeforeUnmount(() => {
    clearInterval(timeInterval)
  })
})
</script>