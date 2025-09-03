import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

export default defineConfig({
  root: process.cwd(),
  publicDir: 'public',
  plugins: [
    vue(),
    {
      name: 'debug-middleware',
      configureServer(server) {
        server.middlewares.use((req, res, next) => {
          console.log('🟢 INCOMING REQUEST:', {
            method: req.method,
            url: req.url,
            headers: {
              host: req.headers.host,
              'user-agent': req.headers['user-agent'],
              accept: req.headers.accept
            },
            timestamp: new Date().toISOString()
          })
          next()
        })
      }
    }
  ],
  server: {
    host: '0.0.0.0',
    port: 3000,
    open: false, // 자동 열기 비활성화
    strictPort: true, // 포트 강제 사용
    cors: true,
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Cache-Control': 'no-cache'
    }
  },
  build: {
    outDir: 'dist',
    assetsDir: 'static',
  },
  define: {
    __VUE_OPTIONS_API__: true,
    __VUE_PROD_DEVTOOLS__: false,
    __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: false,
  },
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src'),
      'vue': 'vue/dist/vue.esm-bundler.js'
    },
  },
})