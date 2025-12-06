// src/stores/userStore.js
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  // 开启持久化（关键！）
  persist: true, // 默认使用 localStorage，key 为 store 的 id（这里是 'user'）

  state: () => ({
    token: '',
    userInfo: {
      name:'',
      username:'',
      purviews:'',
      remark:'',
      state:''
    },
    theme: 'light',
    isLogin: false
  }),

  actions: {
    setToken(token) {
      this.token = token
    },
    logout() {
      this.token = ''
      this.userInfo = null
    }
  }
})