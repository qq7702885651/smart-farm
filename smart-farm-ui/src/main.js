// main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // ✅ 引入
// 引入 Element Plus 全局样式
import 'element-plus/dist/index.css'
// 引入 Element Plus 所有组件
import ElementPlus from 'element-plus'

// 引入 Pinia
import { createPinia } from 'pinia'
// 引入持久化插件
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const app = createApp(App)

// 创建 Pinia 实例
const pinia = createPinia()
// 使用持久化插件
pinia.use(piniaPluginPersistedstate)

// 挂载 Pinia
app.use(pinia)

app.use(router) // ✅ 必须调用！否则 <router-view> 无法识别

// 使用插件
app.use(ElementPlus)

app.mount('#app')