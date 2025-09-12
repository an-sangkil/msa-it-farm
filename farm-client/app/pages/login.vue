<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900 py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div class="text-center">
        <UIcon name="i-heroicons-home" class="w-12 h-12 mx-auto text-primary-600" />
        <h2 class="mt-6 text-3xl font-bold text-gray-900 dark:text-white">
          MSA IT Farm 로그인
        </h2>
        <p class="mt-2 text-sm text-gray-600 dark:text-gray-400">
          농장 관리 시스템에 로그인하세요
        </p>
      </div>

      <UCard>
        <UForm :state="loginForm" class="space-y-6" @submit="handleLogin">
          <UFormGroup label="사용자 ID" name="username" required>
            <UInput 
              v-model="loginForm.username" 
              placeholder="사용자 ID를 입력하세요"
              icon="i-heroicons-user"
            />
          </UFormGroup>

          <UFormGroup label="비밀번호" name="password" required>
            <UInput 
              v-model="loginForm.password" 
              type="password"
              placeholder="비밀번호를 입력하세요"
              icon="i-heroicons-lock-closed"
            />
          </UFormGroup>

          <div>
            <UButton 
              type="submit" 
              block 
              size="lg"
              :loading="isLoading"
              :disabled="!loginForm.username || !loginForm.password"
            >
              로그인
            </UButton>
          </div>

          <!-- 임시 테스트 버튼 -->
          <div class="text-center pt-4 border-t border-gray-200 dark:border-gray-700">
            <p class="text-sm text-gray-500 dark:text-gray-400 mb-2">테스트용</p>
            <UButton 
              variant="outline" 
              size="sm" 
              @click="goToDashboard"
            >
              Dashboard 바로 가기
            </UButton>
          </div>
        </UForm>
      </UCard>
    </div>
  </div>
</template>

<script setup lang="ts">
definePageMeta({
  layout: false
})

const loginForm = reactive({
  username: '',
  password: ''
})

const isLoading = ref(false)

const handleLogin = async () => {
  isLoading.value = true
  
  try {
    // TODO: 실제 로그인 API 호출
    console.log('로그인 시도:', loginForm)
    
    // 임시로 2초 후 대시보드로 이동
    await new Promise(resolve => setTimeout(resolve, 2000))
    
    await navigateTo('/dashboard')
  } catch (error) {
    console.error('로그인 실패:', error)
  } finally {
    isLoading.value = false
  }
}

const goToDashboard = () => {
  navigateTo('/dashboard')
}

useSeoMeta({
  title: '로그인 - MSA IT Farm',
  description: 'MSA IT Farm 농장 관리 시스템 로그인'
})
</script>