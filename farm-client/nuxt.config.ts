// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },

  modules: [
    '@nuxt/content',
    '@nuxt/eslint',
    '@nuxt/image',
    '@nuxt/scripts',
    '@nuxt/test-utils',
    '@nuxt/ui',
    '@pinia/nuxt'
  ],

  runtimeConfig: {
    // Private keys (서버에서만 접근 가능)
    apiSecret: process.env.API_SECRET,
    
    public: {
      // Public keys (클라이언트에서 접근 가능)
      apiBase: process.env.API_BASE || 'http://localhost:8090'
    }
  },

  css: [
    // 글로벌 CSS 파일이 필요한 경우 추가
  ]
})