<template>
  <div id="app">
    <nav style="padding: 20px; background: #f0f0f0; margin-bottom: 20px;">
      <h1>MSA IT Farm - Vue 3 Test</h1>
      <div style="margin-top: 10px;">
        <router-link to="/" style="margin-right: 10px;">Home</router-link>
        <router-link to="/about" style="margin-right: 10px;">About</router-link>
        <router-link to="/login" style="margin-right: 10px;">Login</router-link>
        <router-link to="/notfound">Test 404</router-link>
      </div>
    </nav>
    
    <main style="padding: 20px;">
      <p><strong>Current Route:</strong> {{ $route.path }}</p>
      <router-view></router-view>
    </main>
    
    <footer style="padding: 20px; background: #f0f0f0; margin-top: 20px;">
      <p>Vue 3 + Vite + Vue Router 4 working successfully! 🎉</p>
      <p>Time: {{ currentTime }}</p>
    </footer>
  </div>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue'

export default defineComponent({
  name: 'App',
  setup() {
    const currentTime = ref('')
    
    const updateTime = () => {
      currentTime.value = new Date().toLocaleString()
    }
    
    onMounted(() => {
      console.log('🎉 App component mounted successfully!')
      updateTime()
      setInterval(updateTime, 1000)
    })
    
    return {
      currentTime
    }
  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  min-height: 100vh;
}

a {
  color: #2c3e50;
  text-decoration: none;
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

a.router-link-exact-active {
  color: white;
  background-color: #2c3e50;
}
</style>