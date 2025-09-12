<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            {{ isEditMode ? '관찰 기록 수정' : '관찰 기록 추가' }}
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            {{ isEditMode ? '기존 관찰 기록을 수정합니다' : '새로운 관찰 기록을 추가합니다' }}
          </p>
        </div>
        <UButton variant="outline" to="/observe" icon="i-heroicons-arrow-left">
          목록으로
        </UButton>
      </div>

      <UCard>
        <UForm :state="observationForm" class="space-y-6" @submit="handleSubmit">
          <!-- 기본 정보 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">기본 정보</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="개체식별번호" name="entityIdentificationNumber" required>
                <UInput 
                  v-model="observationForm.entityIdentificationNumber"
                  placeholder="15자리 개체식별번호 입력"
                  maxlength="15"
                />
                <template #help>
                  <span class="text-xs text-gray-500">예: 110123456789012</span>
                </template>
              </UFormGroup>

              <UFormGroup label="관찰일" name="observationDate" required>
                <UInput 
                  v-model="observationForm.observationDate"
                  type="date"
                />
              </UFormGroup>

              <UFormGroup label="관찰 시간" name="observationTime" required>
                <UInput 
                  v-model="observationForm.observationTime"
                  type="time"
                />
              </UFormGroup>

              <UFormGroup label="관찰자" name="observer" required>
                <UInput 
                  v-model="observationForm.observer"
                  placeholder="관찰자 이름"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 건강 지표 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">건강 지표</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="전반적인 건강 상태" name="healthStatus" required>
                <USelect 
                  v-model="observationForm.healthStatus"
                  :options="healthStatusOptions"
                  placeholder="건강 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="체온 (°C)" name="temperature">
                <UInput 
                  v-model.number="observationForm.temperature"
                  type="number"
                  step="0.1"
                  placeholder="예: 38.5"
                />
                <template #help>
                  <span class="text-xs text-gray-500">정상 범위: 38.1-39.1°C</span>
                </template>
              </UFormGroup>

              <UFormGroup label="추정 체중 (kg)" name="weight">
                <UInput 
                  v-model.number="observationForm.weight"
                  type="number"
                  placeholder="추정 체중"
                />
              </UFormGroup>

              <UFormGroup label="맥박 (회/분)" name="pulse">
                <UInput 
                  v-model.number="observationForm.pulse"
                  type="number"
                  placeholder="예: 60"
                />
                <template #help>
                  <span class="text-xs text-gray-500">정상 범위: 48-84회/분</span>
                </template>
              </UFormGroup>

              <UFormGroup label="호흡 (회/분)" name="respiration">
                <UInput 
                  v-model.number="observationForm.respiration"
                  type="number"
                  placeholder="예: 30"
                />
                <template #help>
                  <span class="text-xs text-gray-500">정상 범위: 26-50회/분</span>
                </template>
              </UFormGroup>
            </div>
          </div>

          <!-- 행동 관찰 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">행동 관찰</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="식욕 상태" name="appetite" required>
                <USelect 
                  v-model="observationForm.appetite"
                  :options="appetiteOptions"
                  placeholder="식욕 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="활동성" name="activity" required>
                <USelect 
                  v-model="observationForm.activity"
                  :options="activityOptions"
                  placeholder="활동성을 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="배설 상태" name="excretion">
                <USelect 
                  v-model="observationForm.excretion"
                  :options="excretionOptions"
                  placeholder="배설 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="반추 행동" name="rumination">
                <USelect 
                  v-model="observationForm.rumination"
                  :options="ruminationOptions"
                  placeholder="반추 행동을 선택하세요"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 외형 관찰 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">외형 관찰</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="털 상태" name="coatCondition">
                <USelect 
                  v-model="observationForm.coatCondition"
                  :options="coatConditionOptions"
                  placeholder="털 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="눈 상태" name="eyeCondition">
                <USelect 
                  v-model="observationForm.eyeCondition"
                  :options="eyeConditionOptions"
                  placeholder="눈 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="코 상태" name="noseCondition">
                <USelect 
                  v-model="observationForm.noseCondition"
                  :options="noseConditionOptions"
                  placeholder="코 상태를 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="발굽 상태" name="hoofCondition">
                <USelect 
                  v-model="observationForm.hoofCondition"
                  :options="hoofConditionOptions"
                  placeholder="발굽 상태를 선택하세요"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 관찰 내용 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">상세 관찰 내용</h2>
            <UFormGroup label="관찰 내용" name="observations" required>
              <UTextarea 
                v-model="observationForm.observations"
                placeholder="상세한 관찰 내용을 입력하세요"
                :rows="6"
              />
            </UFormGroup>

            <UFormGroup label="특이사항" name="notes">
              <UTextarea 
                v-model="observationForm.notes"
                placeholder="특이사항이나 추가 메모를 입력하세요"
                :rows="3"
              />
            </UFormGroup>
          </div>

          <!-- 후속 조치 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">후속 조치</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="추천 조치사항" name="recommendedActions">
                <USelect 
                  v-model="observationForm.recommendedActions"
                  :options="actionOptions"
                  placeholder="추천 조치사항을 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="다음 관찰 예정일" name="nextObservationDate">
                <UInput 
                  v-model="observationForm.nextObservationDate"
                  type="date"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 버튼 섹션 -->
          <div class="flex justify-end space-x-4 pt-6 border-t border-gray-200 dark:border-gray-700">
            <UButton variant="outline" to="/observe" :disabled="loading">
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
  title: '관찰 기록 추가/수정 - MSA IT Farm'
})

// 타입 정의
interface ObservationForm {
  entityIdentificationNumber: string
  observationDate: string
  observationTime: string
  observer: string
  healthStatus: string
  temperature: number | null
  weight: number | null
  pulse: number | null
  respiration: number | null
  appetite: string
  activity: string
  excretion: string
  rumination: string
  coatCondition: string
  eyeCondition: string
  noseCondition: string
  hoofCondition: string
  observations: string
  notes: string
  recommendedActions: string
  nextObservationDate: string
}

// 라우터 파라미터
const route = useRoute()
const isEditMode = computed(() => !!route.params.id)

// 반응형 데이터
const loading = ref(false)
const observationForm = reactive<ObservationForm>({
  entityIdentificationNumber: '',
  observationDate: new Date().toISOString().split('T')[0],
  observationTime: new Date().toTimeString().split(' ')[0].substring(0, 5),
  observer: '농장 관리자',
  healthStatus: '',
  temperature: null,
  weight: null,
  pulse: null,
  respiration: null,
  appetite: '',
  activity: '',
  excretion: '',
  rumination: '',
  coatCondition: '',
  eyeCondition: '',
  noseCondition: '',
  hoofCondition: '',
  observations: '',
  notes: '',
  recommendedActions: '',
  nextObservationDate: ''
})

// 옵션 데이터
const healthStatusOptions = [
  { label: '양호', value: 'good' },
  { label: '주의', value: 'caution' },
  { label: '이상', value: 'abnormal' },
  { label: '치료중', value: 'treatment' }
]

const appetiteOptions = [
  { label: '양호', value: 'good' },
  { label: '보통', value: 'normal' },
  { label: '부진', value: 'poor' },
  { label: '거부', value: 'none' }
]

const activityOptions = [
  { label: '활발', value: 'active' },
  { label: '보통', value: 'normal' },
  { label: '둔함', value: 'sluggish' },
  { label: '비활성', value: 'inactive' }
]

const excretionOptions = [
  { label: '정상', value: 'normal' },
  { label: '설사', value: 'diarrhea' },
  { label: '변비', value: 'constipation' },
  { label: '혈변', value: 'blood' }
]

const ruminationOptions = [
  { label: '정상', value: 'normal' },
  { label: '감소', value: 'decreased' },
  { label: '없음', value: 'absent' }
]

const coatConditionOptions = [
  { label: '정상', value: 'normal' },
  { label: '거친', value: 'rough' },
  { label: '털빠짐', value: 'shedding' },
  { label: '피부질환', value: 'skin_disease' }
]

const eyeConditionOptions = [
  { label: '정상', value: 'normal' },
  { label: '분비물', value: 'discharge' },
  { label: '충혈', value: 'red' },
  { label: '흐림', value: 'cloudy' }
]

const noseConditionOptions = [
  { label: '정상', value: 'normal' },
  { label: '분비물', value: 'discharge' },
  { label: '건조', value: 'dry' },
  { label: '출혈', value: 'bleeding' }
]

const hoofConditionOptions = [
  { label: '정상', value: 'normal' },
  { label: '파행', value: 'lameness' },
  { label: '부종', value: 'swelling' },
  { label: '상처', value: 'wound' }
]

const actionOptions = [
  { label: '지속 관찰', value: 'continue_observation' },
  { label: '수의사 진료', value: 'veterinary_care' },
  { label: '사료 조정', value: 'feed_adjustment' },
  { label: '약물 투여', value: 'medication' },
  { label: '격리', value: 'isolation' }
]

// 폼 검증 규칙
const validateForm = (): string[] => {
  const errors: string[] = []
  
  if (!observationForm.entityIdentificationNumber) {
    errors.push('개체식별번호는 필수입니다.')
  } else if (observationForm.entityIdentificationNumber.length !== 15) {
    errors.push('개체식별번호는 15자리여야 합니다.')
  }
  
  if (!observationForm.observationDate) {
    errors.push('관찰일은 필수입니다.')
  }
  
  if (!observationForm.observationTime) {
    errors.push('관찰 시간은 필수입니다.')
  }
  
  if (!observationForm.observer.trim()) {
    errors.push('관찰자는 필수입니다.')
  }
  
  if (!observationForm.healthStatus) {
    errors.push('건강 상태는 필수입니다.')
  }
  
  if (!observationForm.appetite) {
    errors.push('식욕 상태는 필수입니다.')
  }
  
  if (!observationForm.activity) {
    errors.push('활동성은 필수입니다.')
  }
  
  if (!observationForm.observations.trim()) {
    errors.push('관찰 내용은 필수입니다.')
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
      await updateObservation()
    } else {
      await createObservation()
    }
    
    // TODO: 성공 토스트 표시
    await navigateTo('/observe')
    
  } catch (error) {
    console.error('저장 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// API 함수들
const createObservation = async () => {
  const submitData = {
    ...observationForm,
    createdAt: new Date().toISOString()
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('관찰 기록 등록:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const updateObservation = async () => {
  const submitData = {
    ...observationForm,
    id: route.params.id,
    updatedAt: new Date().toISOString()
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('관찰 기록 수정:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const loadObservationData = async () => {
  if (!isEditMode.value) return
  
  const observationId = route.params.id
  
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    console.log('관찰 기록 정보 로드:', observationId)
    
    // 임시 더미 데이터
    Object.assign(observationForm, {
      entityIdentificationNumber: '110123456789012',
      observationDate: '2024-09-12',
      observationTime: '09:30',
      observer: '농장 관리자',
      healthStatus: 'good',
      temperature: 38.5,
      weight: 450,
      pulse: 65,
      respiration: 30,
      appetite: 'good',
      activity: 'active',
      excretion: 'normal',
      rumination: 'normal',
      coatCondition: 'normal',
      eyeCondition: 'normal',
      noseCondition: 'normal',
      hoofCondition: 'normal',
      observations: '전반적으로 건강한 상태를 보이고 있습니다. 식욕이 왕성하고 활동성도 좋습니다.',
      notes: '특이사항 없음',
      recommendedActions: 'continue_observation',
      nextObservationDate: '2024-09-19'
    })
    
  } catch (error) {
    console.error('관찰 기록 정보 로드 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  if (isEditMode.value) {
    loadObservationData()
  }
})
</script>