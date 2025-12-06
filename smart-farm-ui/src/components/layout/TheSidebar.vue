<!-- src/components/layout/TheSidebar.vue -->
<template>
  <el-aside width="200px" class="app-aside">
    <el-menu
      :default-active="$route.path"
      router
      background-color="#333"
      text-color="#fff"
      active-text-color="#ffd04b"
      class="sidebar-menu"
    >
      <el-menu-item index="/lands">
        <el-icon><MenuIcon /></el-icon>
        <span>土地列表</span>
      </el-menu-item>
      <!-- 可继续添加其他菜单项 -->
    </el-menu>
  </el-aside>
</template>

<script setup>
import { Menu as MenuIcon } from '@element-plus/icons-vue'
</script>

<style scoped>
.app-aside {
  background-color: #333;
  height: calc(100vh - 60px);
}

.sidebar-menu :deep(.el-menu-item) {
  padding-left: 20px !important;
}

.sidebar-menu :deep(.el-menu-item.is-active) {
  background-color: #444 !important;
}
</style>