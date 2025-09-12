<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900">
    <!-- Navigation Header -->
    <UContainer>
      <header class="sticky top-0 z-50 bg-white dark:bg-gray-800 shadow-sm border-b border-gray-200 dark:border-gray-700">
        <div class="flex items-center justify-between h-16 px-4">
          <!-- Logo & Brand -->
          <div class="flex items-center space-x-4">
            <NuxtLink to="/dashboard" class="flex items-center space-x-2">
              <UIcon name="i-heroicons-home" class="h-8 w-8 text-primary-600" />
              <h1 class="text-xl font-bold text-gray-900 dark:text-white">MSA IT Farm</h1>
            </NuxtLink>
          </div>

          <!-- Navigation Menu -->
          <nav class="hidden md:flex items-center space-x-6">
            <NuxtLink to="/dashboard" class="nav-link">
              <UIcon name="i-heroicons-chart-pie" class="w-4 h-4" />
              Dashboard
            </NuxtLink>
            <NuxtLink to="/cattle" class="nav-link">
              <UIcon name="i-heroicons-building-storefront" class="w-4 h-4" />
              소 관리
            </NuxtLink>
            <NuxtLink to="/diary" class="nav-link">
              <UIcon name="i-heroicons-document-text" class="w-4 h-4" />
              일지
            </NuxtLink>
            <NuxtLink to="/observe" class="nav-link">
              <UIcon name="i-heroicons-eye" class="w-4 h-4" />
              관찰
            </NuxtLink>
          </nav>

          <!-- User Menu -->
          <div class="flex items-center space-x-4">
            <!-- Theme Toggle -->
            <UColorModeButton />
            
            <!-- User Dropdown -->
            <UDropdown :items="userMenuItems" :popper="{ placement: 'bottom-end' }">
              <UAvatar size="sm" />
            </UDropdown>
          </div>

          <!-- Mobile menu button -->
          <UButton 
            variant="ghost" 
            size="sm" 
            class="md:hidden"
            @click="mobileMenuOpen = !mobileMenuOpen"
          >
            <UIcon name="i-heroicons-bars-3" class="w-5 h-5" />
          </UButton>
        </div>

        <!-- Mobile Navigation -->
        <div v-if="mobileMenuOpen" class="md:hidden border-t border-gray-200 dark:border-gray-700">
          <div class="px-4 py-3 space-y-2">
            <NuxtLink to="/dashboard" class="mobile-nav-link" @click="mobileMenuOpen = false">
              <UIcon name="i-heroicons-chart-pie" class="w-4 h-4" />
              Dashboard
            </NuxtLink>
            <NuxtLink to="/cattle" class="mobile-nav-link" @click="mobileMenuOpen = false">
              <UIcon name="i-heroicons-building-storefront" class="w-4 h-4" />
              소 관리
            </NuxtLink>
            <NuxtLink to="/diary" class="mobile-nav-link" @click="mobileMenuOpen = false">
              <UIcon name="i-heroicons-document-text" class="w-4 h-4" />
              일지
            </NuxtLink>
            <NuxtLink to="/observe" class="mobile-nav-link" @click="mobileMenuOpen = false">
              <UIcon name="i-heroicons-eye" class="w-4 h-4" />
              관찰
            </NuxtLink>
          </div>
        </div>
      </header>
    </UContainer>

    <!-- Main Content -->
    <main class="flex-1">
      <slot />
    </main>

    <!-- Footer -->
    <footer class="bg-white dark:bg-gray-800 border-t border-gray-200 dark:border-gray-700 mt-auto">
      <UContainer>
        <div class="py-4 text-center text-sm text-gray-600 dark:text-gray-400">
          &copy; {{ currentYear }} MSA IT Farm - Nuxt 4 버전
        </div>
      </UContainer>
    </footer>
  </div>
</template>

<script setup lang="ts">
const mobileMenuOpen = ref(false)
const currentYear = new Date().getFullYear()

const userMenuItems = [
  [
    {
      label: '프로필',
      icon: 'i-heroicons-user',
      click: () => navigateTo('/profile')
    }
  ],
  [
    {
      label: '로그아웃',
      icon: 'i-heroicons-arrow-right-on-rectangle',
      click: () => navigateTo('/login')
    }
  ]
]
</script>

<style scoped>
.nav-link {
  @apply flex items-center space-x-2 px-3 py-2 text-sm font-medium text-gray-700 dark:text-gray-300 hover:text-primary-600 dark:hover:text-primary-400 rounded-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors;
}

.nav-link.router-link-active {
  @apply text-primary-600 dark:text-primary-400 bg-primary-50 dark:bg-primary-900;
}

.mobile-nav-link {
  @apply flex items-center space-x-3 px-3 py-2 text-base font-medium text-gray-700 dark:text-gray-300 hover:text-primary-600 dark:hover:text-primary-400 rounded-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors;
}

.mobile-nav-link.router-link-active {
  @apply text-primary-600 dark:text-primary-400 bg-primary-50 dark:bg-primary-900;
}
</style>