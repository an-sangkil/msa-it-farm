<template>
  <div>
    <UContainer class="py-8">
      <div class="text-center">
        <UIcon name="i-heroicons-home" class="w-16 h-16 mx-auto text-primary-500 mb-4" />
        <h1 class="text-3xl font-bold text-gray-900 dark:text-white mb-4">
          MSA IT Farm에 오신 것을 환영합니다
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-400 mb-8">
          농장 관리를 위한 통합 플랫폼입니다
        </p>
        
        <div class="space-y-4">
          <UButton size="lg" to="/dashboard">
            Dashboard로 이동
          </UButton>
          <div class="text-sm text-gray-500 dark:text-gray-400">
            또는 <NuxtLink to="/login" class="text-primary-600 hover:text-primary-500">로그인</NuxtLink>하세요
          </div>
        </div>
      </div>
    </UContainer>
  </div>
</template>

<script setup lang="ts">
// 홈페이지 메타 데이터
useSeoMeta({
  title: 'MSA IT Farm - 농장 관리 시스템',
  description: '효율적인 농장 관리를 위한 통합 플랫폼'
})
</script>