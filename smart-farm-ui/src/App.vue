<!-- src/App.vue -->
<script setup>
import { ref } from 'vue'
import Login from './components/user/Login.vue'
import Register from './components/user/Register.vue'
import { useUserStore } from '@/stores/userStore'
import TheHeader from './components/layout/TheHeader.vue'
import TheSidebar from './components/layout/TheSidebar.vue'

const store = useUserStore()

const switchState = ref(true)

const toggleForm = () => {
  switchState.value = !switchState.value
}

const handleLoginSuccess = () => {
  store.isLogin = true
  // 如果你有 login action，建议用 store.login()
}
</script>

<template>
  <div class="app">
    <!-- 登录/注册 -->
    <div v-if="!store.isLogin" class="user-container">
      <div class="user-form-wrapper">
        <Login 
          v-if="switchState" 
          @login-success="handleLoginSuccess" 
          @toggle-form="toggleForm" 
        />
        <Register 
          v-else 
          @register-success="handleLoginSuccess" 
          @toggle-form="toggleForm" 
        />
      </div>
    </div>

    <!-- 已登录：主界面 -->
    <div v-else class="layout-container">
      <TheHeader />
      <div class="main-container">
        <TheSidebar />
        <main class="app-main">
          <router-view />
        </main>
      </div>
    </div>
  </div>
</template>

<style scoped>
.app {
  height: 100vh;
  margin: 0;
  padding: 0;
}

.user-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #e4edf9 100%);
  padding: 20px;
}

.user-form-wrapper {
  width: 100%;
  max-width: 400px;
}

.layout-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.main-container {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.app-main {
  flex: 1;
  background-color: #f9fafb;
  padding: 20px;
  overflow-y: auto;
}
</style>