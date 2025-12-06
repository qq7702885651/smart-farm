<script setup>
import { ref, onMounted } from 'vue'
import { addCycler, getCyclers, updateCycler, search } from "@/api/cyclers/cyclers.js"

// 数据列表
let list = ref([])
let total = ref(0)

// 搜索关键字
let keyword = ref("")

// 弹窗控制
let dialogVisible = ref(false)

// true = 新增；false = 修改
let isAdd = ref(true)

// 编辑/新增表单
let editForm = ref({
  title: "",
  days: "",
  remark: "",
  state: 1
})

// 获取列表
const loadData = async () => {
  let res = await getCyclers(0, 100)
  list.value = res
  total.value = res.length
}

// 日期格式化
const formatDate = (time) => {
  if (!time) return ""
  const date = new Date(time)
  const y = date.getFullYear()
  const m = String(date.getMonth() + 1).padStart(2, "0")
  const d = String(date.getDate()).padStart(2, "0")
  const hh = String(date.getHours()).padStart(2, "0")
  const mm = String(date.getMinutes()).padStart(2, "0")
  const ss = String(date.getSeconds()).padStart(2, "0")
  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
}

// 点击搜索
const handleSearch = async () => {
  if (!keyword.value) {
    loadData()
    return
  }
  let res = await search(keyword.value)
  list.value = res
}

// 打开新增弹窗
const openAdd = () => {
  isAdd.value = true
  editForm.value = {
    title: "",
    days: "",
    remark: "",
    state: 1
  }
  dialogVisible.value = true
}

// 打开编辑弹窗
const openEdit = (row) => {
  isAdd.value = false
  editForm.value = { ...row }
  dialogVisible.value = true
}

// 保存（新增 or 修改）
const save = async () => {
  try {
    if (isAdd.value) {
      await addCycler(editForm.value)
    } else {
      await updateCycler(editForm.value)
    }

    // 新增/修改成功后：
    dialogVisible.value = false // 关闭弹窗
    await loadData() // 刷新列表
  } catch (error) {
    console.error('操作失败:', error.message)
  }
}



onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="cyclers-container">

    <!-- 顶部：新增 + 搜索 -->
    <div style="margin-bottom: 15px; display: flex; gap: 10px;">
      <el-button type="primary" @click="openAdd">+ 新增周期</el-button>

      <el-input
          v-model="keyword"
          placeholder="输入周期名称搜索"
          clearable
          style="width: 250px;"
      />
      <el-button type="primary" @click="handleSearch">查询</el-button>
      <el-button @click="loadData">重置</el-button>
    </div>

    <!-- 📄 表格 -->
    <el-table :data="list" border style="width: 100%;">
      <el-table-column prop="title" label="周期名称" width="180" />
      <el-table-column prop="days" label="天数" width="120" />
      <el-table-column prop="remark" label="备注" />

      <el-table-column label="状态" width="120">
        <template #default="scope">
          <el-tag :type="scope.row.state === 1 ? 'success' : 'info'">
            {{ scope.row.state === 1 ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" width="180">
        <template #default="scope">
          {{ formatDate(scope.row.createdtime) }}
        </template>
      </el-table-column>

      <el-table-column label="更新时间" width="180">
        <template #default="scope">
          {{ formatDate(scope.row.updatedtime) }}
        </template>
      </el-table-column>

      <!-- 操作列 -->
      <el-table-column label="操作" width="140">
        <template #default="scope">
          <el-button size="small" type="primary" @click="openEdit(scope.row)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- ✏ 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" :title="isAdd ? '新增周期' : '编辑周期'" width="400px">

      <el-form :model="editForm" label-width="90px">
        <el-form-item label="周期名称">
          <el-input v-model="editForm.title" />
        </el-form-item>

        <el-form-item label="天数">
          <el-input type="number" v-model="editForm.days" />
        </el-form-item>

        <el-form-item label="备注">
          <el-input type="textarea" v-model="editForm.remark" />
        </el-form-item>

        <el-form-item label="状态">
          <el-switch
              v-model="editForm.state"
              :active-value="1"
              :inactive-value="0"
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          {{ isAdd ? '新增' : '保存修改' }}
        </el-button>
      </template>
    </el-dialog>

  </div>
</template>

<style scoped>
.cyclers-container {
  padding: 20px;
}
</style>
