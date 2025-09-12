<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            {{ isEditMode ? '일지 수정' : '일지 작성' }}
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            {{ isEditMode ? '기존 일지를 수정합니다' : '새로운 농장 일지를 작성합니다' }}
          </p>
        </div>
        <UButton variant="outline" to="/diary" icon="i-heroicons-arrow-left">
          목록으로
        </UButton>
      </div>

      <UCard>
        <UForm :state="diaryForm" class="space-y-6" @submit="handleSubmit">
          <!-- 기본 정보 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">기본 정보</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="제목" name="title" required>
                <UInput 
                  v-model="diaryForm.title"
                  placeholder="일지 제목을 입력하세요"
                />
              </UFormGroup>

              <UFormGroup label="작업일" name="workDate" required>
                <UInput 
                  v-model="diaryForm.workDate"
                  type="date"
                />
              </UFormGroup>

              <UFormGroup label="카테고리" name="category" required>
                <USelect 
                  v-model="diaryForm.category"
                  :options="categoryOptions"
                  placeholder="카테고리를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="날씨" name="weather">
                <USelect 
                  v-model="diaryForm.weather"
                  :options="weatherOptions"
                  placeholder="날씨를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="온도" name="temperature">
                <UInput 
                  v-model.number="diaryForm.temperature"
                  type="number"
                  placeholder="온도 (°C)"
                />
              </UFormGroup>

              <UFormGroup label="습도" name="humidity">
                <UInput 
                  v-model.number="diaryForm.humidity"
                  type="number"
                  placeholder="습도 (%)"
                  min="0"
                  max="100"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 작업 내용 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">작업 내용</h2>
            <UFormGroup label="작업 요약" name="summary" required>
              <UInput 
                v-model="diaryForm.summary"
                placeholder="작업에 대한 간단한 요약을 입력하세요"
              />
            </UFormGroup>

            <UFormGroup label="상세 내용" name="content" required>
              <UTextarea 
                v-model="diaryForm.content"
                placeholder="작업의 상세한 내용을 입력하세요"
                :rows="10"
              />
            </UFormGroup>
          </div>

          <!-- 시간 기록 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">시간 기록</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="작업 시작 시간" name="startTime">
                <UInput 
                  v-model="diaryForm.startTime"
                  type="time"
                />
              </UFormGroup>

              <UFormGroup label="작업 종료 시간" name="endTime">
                <UInput 
                  v-model="diaryForm.endTime"
                  type="time"
                />
              </UFormGroup>
            </div>

            <div v-if="workDuration" class="mt-2 text-sm text-gray-600 dark:text-gray-400">
              총 작업 시간: {{ workDuration }}
            </div>
          </div>

          <!-- 특이사항 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">특이사항</h2>
            <UFormGroup label="특이사항" name="notes">
              <UTextarea 
                v-model="diaryForm.notes"
                placeholder="특이사항이나 추가 메모를 입력하세요"
                :rows="4"
              />
            </UFormGroup>
          </div>

          <!-- 버튼 섹션 -->
          <div class="flex justify-end space-x-4 pt-6 border-t border-gray-200 dark:border-gray-700">
            <UButton variant="outline" to="/diary" :disabled="loading">
              취소
            </UButton>
            <UButton 
              type="submit" 
              :loading="loading"
              icon="i-heroicons-check"
            >
              {{ isEditMode ? '수정' : '등록' }}
            </UButton>
          </div>
        </UForm>
      </UCard>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 페이지 메타데이터
useSeoMeta({
  title: '일지 작성/수정 - MSA IT Farm'
})

// 타입 정의
interface DiaryForm {
  title: string
  workDate: string
  category: string
  weather: string
  temperature: number | null
  humidity: number | null
  summary: string
  content: string
  startTime: string
  endTime: string
  notes: string
}

// 라우터 파라미터
const route = useRoute()
const isEditMode = computed(() => !!route.params.id)

// 반응형 데이터
const loading = ref(false)
const diaryForm = reactive<DiaryForm>({
  title: '',
  workDate: new Date().toISOString().split('T')[0], // 오늘 날짜로 초기화
  category: '',
  weather: '',
  temperature: null,
  humidity: null,
  summary: '',
  content: '',
  startTime: '',
  endTime: '',
  notes: ''
})

// 옵션 데이터
const categoryOptions = [
  { label: '사료 급여', value: 'feed' },
  { label: '건강 관리', value: 'health' },
  { label: '청소', value: 'cleaning' },
  { label: '번식 관리', value: 'breeding' },
  { label: '기타', value: 'etc' }
]

const weatherOptions = [
  { label: '맑음', value: 'sunny' },
  { label: '흐림', value: 'cloudy' },
  { label: '비', value: 'rainy' },
  { label: '눈', value: 'snowy' },
  { label: '안개', value: 'foggy' }
]

// 계산된 속성
const workDuration = computed(() => {
  if (!diaryForm.startTime || !diaryForm.endTime) return null
  
  const start = new Date(`2000-01-01 ${diaryForm.startTime}`)
  const end = new Date(`2000-01-01 ${diaryForm.endTime}`)
  
  if (end <= start) return null
  
  const diffMs = end.getTime() - start.getTime()
  const hours = Math.floor(diffMs / (1000 * 60 * 60))
  const minutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60))
  
  return `${hours}시간 ${minutes}분`
})

// 폼 검증 규칙
const validateForm = (): string[] => {
  const errors: string[] = []
  
  if (!diaryForm.title.trim()) {
    errors.push('제목은 필수입니다.')
  }
  
  if (!diaryForm.workDate) {
    errors.push('작업일은 필수입니다.')
  }
  
  if (!diaryForm.category) {
    errors.push('카테고리는 필수입니다.')
  }
  
  if (!diaryForm.summary.trim()) {
    errors.push('작업 요약은 필수입니다.')
  }
  
  if (!diaryForm.content.trim()) {
    errors.push('상세 내용은 필수입니다.')
  }
  
  return errors
}

// 이벤트 핸들러
const handleSubmit = async () => {
  const errors = validateForm()
  
  if (errors.length > 0) {
    // TODO: 에러 토스트 표시
    console.error('검증 실패:', errors)
    return
  }
  
  loading.value = true
  
  try {
    if (isEditMode.value) {
      await updateDiary()
    } else {
      await createDiary()
    }
    
    // TODO: 성공 토스트 표시
    await navigateTo('/diary')
    
  } catch (error) {
    console.error('저장 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// API 함수들
const createDiary = async () => {
  const submitData = {
    ...diaryForm,
    author: '농장 관리자', // TODO: 로그인한 사용자 정보에서 가져오기
    createdAt: new Date().toISOString()
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('일지 등록:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const updateDiary = async () => {
  const submitData = {
    ...diaryForm,
    id: route.params.id,
    updatedAt: new Date().toISOString()
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('일지 수정:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const loadDiaryData = async () => {
  if (!isEditMode.value) return
  
  const diaryId = route.params.id
  
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    console.log('일지 정보 로드:', diaryId)
    
    // 임시 더미 데이터
    Object.assign(diaryForm, {
      title: '농장 일지 #1 - 사료 급여',
      workDate: '2024-09-12',
      category: 'feed',
      weather: 'sunny',
      temperature: 25,
      humidity: 60,
      summary: '오전 사료 급여를 완료했습니다',
      content: '오전 8시부터 모든 축사에 사료를 급여했습니다. 소들의 식욕이 왕성하고 건강한 상태를 확인했습니다. A동에서 F동까지 순차적으로 진행했으며, 특별한 이상은 발견되지 않았습니다.',
      startTime: '08:00',
      endTime: '10:30',
      notes: '특이사항 없음'
    })
    
  } catch (error) {
    console.error('일지 정보 로드 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  if (isEditMode.value) {
    loadDiaryData()
  }
})
</script>