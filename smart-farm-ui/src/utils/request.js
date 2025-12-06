// src/utils/request.js
import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
  baseURL: 'http://localhost:8080', // 后端 API 基地址（根据你的 Spring Boot 地址修改）
  timeout: 10000, // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 可在此添加 token、loading 等
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    console.error('请求发送失败:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // 根据你的后端返回结构调整
    const { code, data, message } = response.data

    if (code === 200) {
      return data // 直接返回业务数据
    } else {
      // 业务逻辑错误（如登录失败）
      console.error('业务错误:', message)
      return Promise.reject(new Error(message || '请求失败'))
    }
  },
  (error) => {
    // 网络错误或 HTTP 状态码非 2xx
    if (error.response) {
      const status = error.response.status
      switch (status) {
        case 401:
          console.error('未授权，请重新登录')
          // 可跳转到登录页
          break
        case 403:
          console.error('拒绝访问')
          break
        case 500:
          console.error('服务器内部错误')
          break
        default:
          console.error(`请求失败: ${status}`)
      }
    } else if (error.request) {
      console.error('网络错误，请检查连接')
    }
    return Promise.reject(error)
  }
)

export default service