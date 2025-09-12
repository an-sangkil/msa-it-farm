<template>
  <div>
    <UContainer class="py-8">
      <!-- Page Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-2">
            {{ isEditMode ? '소 정보 수정' : '소 등록' }}
          </h1>
          <p class="text-gray-600 dark:text-gray-400">
            {{ isEditMode ? '소의 정보를 수정합니다' : '새로운 소를 등록합니다' }}
          </p>
        </div>
        <UButton variant="outline" to="/cattle" icon="i-heroicons-arrow-left">
          목록으로
        </UButton>
      </div>

      <UCard>
        <UForm :state="cattleForm" class="space-y-6" @submit="handleSubmit">
          <!-- 기본 정보 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">기본 정보</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="개체식별번호" name="entityIdentificationNumber" required>
                <UInput 
                  v-model="cattleForm.entityIdentificationNumber"
                  placeholder="15자리 개체식별번호 입력"
                  :disabled="isEditMode"
                  maxlength="15"
                />
                <template #help>
                  <span class="text-xs text-gray-500">예: 110123456789012</span>
                </template>
              </UFormGroup>

              <UFormGroup label="관리번호" name="entityManagementNumber" required>
                <UInput 
                  v-model="cattleForm.entityManagementNumber"
                  placeholder="8자리 관리번호 입력"
                  :disabled="isEditMode"
                  maxlength="8"
                />
                <template #help>
                  <span class="text-xs text-gray-500">예: 20240001</span>
                </template>
              </UFormGroup>

              <UFormGroup label="암수구분" name="gender" required>
                <USelect 
                  v-model="cattleForm.gender"
                  :options="genderOptions"
                  placeholder="암수구분을 선택하세요"
                />
              </UFormGroup>

              <UFormGroup label="생년월일" name="birthDay">
                <UInput 
                  v-model="cattleForm.birthDay"
                  type="date"
                />
              </UFormGroup>

              <UFormGroup label="개월수" name="numberOfMonth">
                <UInput 
                  v-model.number="cattleForm.numberOfMonth"
                  type="number"
                  placeholder="개월수 입력"
                  min="0"
                />
              </UFormGroup>

              <UFormGroup label="사육방" name="roomNumber">
                <UInput 
                  v-model="cattleForm.roomNumber"
                  placeholder="사육방 번호 입력"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 번식 정보 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">번식 정보</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <UFormGroup label="분만횟수" name="calvesCount">
                <UInput 
                  v-model.number="cattleForm.calvesCount"
                  type="number"
                  placeholder="분만횟수 입력"
                  min="0"
                />
                <template #help>
                  <span class="text-xs text-gray-500">실제 분만횟수 (저장시 +1 처리됨)</span>
                </template>
              </UFormGroup>

              <UFormGroup label="수정횟수" name="inseminationCount">
                <UInput 
                  v-model.number="cattleForm.inseminationCount"
                  type="number"
                  placeholder="수정횟수 입력"
                  min="0"
                />
              </UFormGroup>

              <UFormGroup label="분만예정일" name="expectedDateChildbirth">
                <UInput 
                  v-model="cattleForm.expectedDateChildbirth"
                  type="date"
                />
              </UFormGroup>

              <UFormGroup label="모 개체식별번호" name="parentMomNo">
                <UInput 
                  v-model="cattleForm.parentMomNo"
                  placeholder="모 개체의 식별번호 입력"
                  maxlength="15"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 추가 정보 섹션 -->
          <div>
            <h2 class="text-xl font-semibold text-gray-900 dark:text-white mb-4">추가 정보</h2>
            <div class="grid grid-cols-1 gap-6">
              <UFormGroup label="특이사항" name="notes">
                <UTextarea 
                  v-model="cattleForm.notes"
                  placeholder="특이사항이나 메모를 입력하세요"
                  :rows="4"
                />
              </UFormGroup>
            </div>
          </div>

          <!-- 버튼 섹션 -->
          <div class="flex justify-end space-x-4 pt-6 border-t border-gray-200 dark:border-gray-700">
            <UButton variant="outline" to="/cattle" :disabled="loading">
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
  title: '소 등록/수정 - MSA IT Farm'
})

// 타입 정의
interface CattleForm {
  entityIdentificationNumber: string
  entityManagementNumber: string
  gender: 'MALE' | 'FEMALE' | ''
  birthDay: string
  numberOfMonth: number | null
  roomNumber: string
  calvesCount: number | null
  inseminationCount: number | null
  expectedDateChildbirth: string
  parentMomNo: string
  notes: string
}

// 라우터 파라미터
const route = useRoute()
const isEditMode = computed(() => !!route.params.id)

// 반응형 데이터
const loading = ref(false)
const cattleForm = reactive<CattleForm>({
  entityIdentificationNumber: '',
  entityManagementNumber: '',
  gender: '',
  birthDay: '',
  numberOfMonth: null,
  roomNumber: '',
  calvesCount: null,
  inseminationCount: null,
  expectedDateChildbirth: '',
  parentMomNo: '',
  notes: ''
})

// 옵션 데이터
const genderOptions = [
  { label: '수컷', value: 'MALE' },
  { label: '암컷', value: 'FEMALE' }
]

// 폼 검증 규칙
const validateForm = (): string[] => {
  const errors: string[] = []
  
  if (!cattleForm.entityIdentificationNumber) {
    errors.push('개체식별번호는 필수입니다.')
  } else if (cattleForm.entityIdentificationNumber.length !== 15) {
    errors.push('개체식별번호는 15자리여야 합니다.')
  }
  
  if (!cattleForm.entityManagementNumber) {
    errors.push('관리번호는 필수입니다.')
  } else if (cattleForm.entityManagementNumber.length !== 8) {
    errors.push('관리번호는 8자리여야 합니다.')
  }
  
  if (!cattleForm.gender) {
    errors.push('암수구분은 필수입니다.')
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
      await updateCattle()
    } else {
      await createCattle()
    }
    
    // TODO: 성공 토스트 표시
    await navigateTo('/cattle')
    
  } catch (error) {
    console.error('저장 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// API 함수들
const createCattle = async () => {
  // 분만횟수 처리 (UI에서는 실제 횟수, DB에는 +1하여 저장)
  const submitData = {
    ...cattleForm,
    calvesCount: (cattleForm.calvesCount || 0) + 1
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('소 등록:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const updateCattle = async () => {
  // 분만횟수 처리
  const submitData = {
    ...cattleForm,
    calvesCount: (cattleForm.calvesCount || 0) + 1
  }
  
  // TODO: 실제 API 호출로 대체
  console.log('소 수정:', submitData)
  await new Promise(resolve => setTimeout(resolve, 2000))
}

const loadCattleData = async () => {
  if (!isEditMode.value) return
  
  const { entityManagementNumber, entityIdentificationNumber } = route.params
  
  loading.value = true
  
  try {
    // TODO: 실제 API 호출로 대체
    console.log('소 정보 로드:', { entityManagementNumber, entityIdentificationNumber })
    
    // 임시 더미 데이터
    Object.assign(cattleForm, {
      entityIdentificationNumber: entityIdentificationNumber as string,
      entityManagementNumber: entityManagementNumber as string,
      gender: 'FEMALE',
      birthDay: '2022-03-15',
      numberOfMonth: 24,
      roomNumber: 'A-1',
      calvesCount: 2, // DB에서는 3, UI에서는 2로 표시
      inseminationCount: 2,
      expectedDateChildbirth: '2024-12-15',
      parentMomNo: '110123456789001',
      notes: '건강한 상태입니다.'
    })
    
  } catch (error) {
    console.error('소 정보 로드 실패:', error)
    // TODO: 에러 토스트 표시
  } finally {
    loading.value = false
  }
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  if (isEditMode.value) {
    loadCattleData()
  }
})
</script>

<style scoped>
/* 커스텀 스타일이 필요한 경우 */
</style>