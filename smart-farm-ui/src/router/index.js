// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'

const Lands = () => import('@/views/lands/Lands.vue')

const routes = [
  {
    path: '/lands',
    name: 'Lands',        // ✅ name 必须是字符串
    component: Lands      // ✅ 拼写正确 + 是有效的组件加载函数
  },
  // 404 页面
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('@/views/Error/NotFound.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router