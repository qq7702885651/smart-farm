<!-- src/components/layout/TheHeader.vue -->
<template>
  <el-header class="app-header">
    <div class="header-left">
      <h2 class="logo-text">土地管理系统</h2>
    </div>
    <div class="header-right">
      <el-dropdown>
        <span class="user-info">
          <el-icon><User /></el-icon>
          {{ userInfo.username }}
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item divided @click="handleLogout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script setup>
import { User } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores/userStore'
import { useRouter } from 'vue-router'

const store = useUserStore()
const router = useRouter()

// 用户信息（实际项目中可从 store 获取）
const userInfo = {
  username: store.userInfo?.username || '管理员'
}

const handleLogout = () => {
  store.$reset() // 或 store.logout()，取决于你的 store 实现
  router.push('/') // 跳转到登录页
}
</script>

<style scoped>
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background-color: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  height: 60px;
}

.logo-text {
  margin: 0;
  font-size: 20px;
  font-weight: 600;
  color: #1989fa;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  font-weight: 500;
  color: #333;
}
</style>