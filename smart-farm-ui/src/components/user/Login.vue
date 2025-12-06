<!-- src/components/user/Login.vue -->
 <script setup>
import { ref,onMounted } from 'vue'
import { loginUser } from '@/api/user/user'
import { useUserStore } from '@/stores/userStore'
import { ElMessage, ElMessageBox } from 'element-plus'

// 声明 emit 事件
const emit = defineEmits(['login-success', 'toggle-form'])

const store = useUserStore()

const manager = ref({
    username:'',
    passw:''
})


const login = async() =>{
    if(manager.value.username === '' && manager.value.passw === ''){
        alert('密码或者账号不能为空')
        return
    }

   try{
        let res = await loginUser(manager.value)
        emit('login-success') // 通知父组件：已登录
        store.$state.userInfo.username = res.username
        store.$state.userInfo.name = res.remark
        store.$state.userInfo.purviews = res.purviews
   }catch(error) {
        ElMessage.warning('登录失败')
   }
    
}
</script>

<template>
  <el-card class="login-card">
    <h2 class="login-title">用户登录</h2>

    <el-form :model="manager" @submit.prevent="login" class="login-form">
      <el-form-item>
        <el-input
          v-model="manager.username"
          type="text"
          placeholder="请输入账号"
          size="large"
          clearable
        />
      </el-form-item>

      <el-form-item>
        <el-input
          v-model="manager.passw"
          type="password"
          placeholder="请输入密码"
          size="large"
          show-password
          clearable
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          size="large"
          class="login-btn"
          native-type="submit"
          :loading="loading"
          style="width: 100%"
        >
          登录
        </el-button>
      </el-form-item>

      <div class="register-link">
        <el-button text @click="$emit('toggle-form')">
          没有账号？去注册
        </el-button>
      </div>
    </el-form>
  </el-card>
</template>


<style scoped>
.login-card {
  width: 100%;
  max-width: 400px;
  border-radius: 16px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  padding: 32px;
  background: white;
  margin: 0 auto; /* 添加居中 */
}

.login-title {
  text-align: center;
  margin-bottom: 28px;
  font-size: 24px;
  font-weight: 600;
  color: #1d3557;
}

.login-form .el-form-item {
  margin-bottom: 20px;
}

.login-btn {
  margin-top: 8px;
  font-weight: 600;
  width: 100%;
}

.register-link {
  text-align: center;
  margin-top: 16px;
}

.register-link .el-button {
  font-size: 14px;
  color: #409eff;
  padding: 0;
}

/* 确保输入框样式正确 */
:deep(.el-input__wrapper) {
  border-radius: 10px;
  width: 100%;
}
</style>