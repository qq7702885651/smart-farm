<!-- src/components/user/Register.vue -->
<template>
  <div class="register-form">
    <h2>用户注册</h2>
    <el-button @click="$emit('toggle-form')">已有账号？去登录</el-button>
    <el-button type="primary" @click="simulateRegister">模拟注册成功</el-button>
  </div>
</template>

<script setup>
const emit = defineEmits(['register-success', 'toggle-form'])

const simulateRegister = () => {
  console.log('注册成功！')
  emit('register-success') // 通知父组件：已登录（注册后自动登录）
}
</script>

<style scoped>
.register-form {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 300px;
}
</style>