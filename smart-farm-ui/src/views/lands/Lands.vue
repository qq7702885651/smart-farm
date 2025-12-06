<script setup>
import { ref, onMounted, computed } from 'vue'
import { pageLands, deleteLand, searchLand, superDeleteLand } from '@/api/lands/lands'
import { ElTable, ElTableColumn, ElTag, ElImage, ElButton, ElInput, ElSelect, ElOption, ElPagination } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import LandFormDialog from '@/components/layout/LandFormDialog.vue' // 👈 引入组件

const lands = ref([])

const searchQuery = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const filterRecommend = ref('all')
const filterCategory = ref('all')
const filterStatus = ref('all')

// 弹窗控制
const dialogVisible = ref(false)
const dialogMode = ref('add')
const editRowData = ref({})

onMounted(async () => {
  fetchData()
})

const fetchData = async () => {
  try {
    const res = await pageLands(
      currentPage.value,
      pageSize.value,
      searchQuery.value,
      filterRecommend.value,
      filterCategory.value,
      filterStatus.value
    )
    lands.value = Array.isArray(res) ? res : []
  } catch (error) {
    console.error('获取土地数据失败:', error)
    ElMessage.error('获取土地数据失败')
  }
}

const filteredLands = computed(() => {
  return lands.value.filter(land =>
    land.title.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
})

// 打开弹窗
const openDialog = (mode, row = {}) => {
  dialogMode.value = mode
  editRowData.value = { ...row }
  dialogVisible.value = true
}

// 编辑
const handleEdit = (index, row) => {
  openDialog('edit', row)
}

// 删除
const handleDelete = async (index, row) => {
  try {
    await ElMessageBox.confirm('确定要删除这条记录吗？', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    if(row.state === 1){
       await deleteLand(row.uid)
        ElMessage.success('下架成功')
        fetchData() // 重新加载，避免索引错乱
        return
    }
    if(row.state === 0){
       await superDeleteLand(row.uid)
        ElMessage.success('删除成功')
        fetchData() // 重新加载，避免索引错乱
        return
    }

   
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

const handleDisable = (index, row) => {
  ElMessage.warning('该功能尚未实现')
}

// 弹窗保存成功后刷新列表
const handleFormSuccess = () => {
  fetchData()
}
</script>

<template>
  <div class="lands-container">
    <h2>土地管理（共 {{ lands.length }} 条）</h2>

    <!-- Filters -->
    <div style="margin-bottom: 20px;">
      <el-input
        v-model="searchQuery"
        placeholder="搜索..."
        style="width: 200px; margin-right: 10px;"
        @input="fetchData"
      />
      <el-button type="primary" style="margin-right: 10px;">搜索</el-button>
      <el-select
        v-model="filterRecommend"
        placeholder="全部推荐"
        style="width: 150px; margin-right: 10px;"
        @change="fetchData"
      >
        <el-option label="全部推荐" value="all" />
        <el-option label="推荐" value="true" />
        <el-option label="不推荐" value="false" />
      </el-select>
      <el-select
        v-model="filterCategory"
        placeholder="全部类目"
        style="width: 150px; margin-right: 10px;"
        @change="fetchData"
      >
        <el-option label="全部类目" value="all" />
        <!-- 可动态加载类目 -->
      </el-select>
      <el-select
        v-model="filterStatus"
        placeholder="全部状态"
        style="width: 150px; margin-right: 10px;"
        @change="fetchData"
      >
        <el-option label="全部状态" value="all" />
        <el-option label="上架" value="1" />
        <el-option label="下架" value="0" />
      </el-select>
      <el-button type="primary" @click="openDialog('add')">添加土地</el-button>
    </div>

    <div class="content" style="background-color: #fff;">
      <el-table
        :data="filteredLands"
        style="width: 100%"
        empty-text="暂无土地数据"
        stripe
        border
      >
        <el-table-column label="预览图" width="120">
          <template #default="scope">
            <el-image
              :src="scope.row.img"
              fit="cover"
              style="width: 80px; height: 60px; border-radius: 4px"
              :preview-src-list="[scope.row.img]"
              preview-teleported
            />
          </template>
        </el-table-column>

        <el-table-column prop="title" label="土地名称" width="180" />
        <el-table-column prop="intro" label="简介" min-width="200" show-overflow-tooltip />
        <el-table-column label="价格（元）" width="100">
          <template #default="scope">
            <strong>¥{{ scope.row.price }}</strong>
          </template>
        </el-table-column>
        <el-table-column prop="stock" label="库存" width="80" />
        <el-table-column prop="norms" label="规格" width="120" />
        <el-table-column prop="location" label="位置" width="120" />
        <el-table-column label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.state === 1 ? 'success' : 'danger'">
              {{ scope.row.state === 1 ? '上架' : '下架' }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="280">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <el-button size="small" type="warning" @click="handleDisable(scope.$index, scope.row)">
              {{ scope.row.state === 1 ? '停用' : '启用' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        background
        layout="prev, pager, next"
        :total="lands.length"
        :page-size="pageSize"
        v-model:currentPage="currentPage"
        @current-change="fetchData"
        class="pagination"
      />
    </div>

    <!-- 封装后的弹窗组件 -->
    <LandFormDialog
      v-model="dialogVisible"
      :mode="dialogMode"
      :data="editRowData"
      @success="handleFormSuccess"
    />
  </div>
</template>

<style scoped>
.lands-container {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.lands-container h2 {
  margin-bottom: 20px;
  font-size: 20px;
  color: #333;
}

.pagination {
  margin-top: 16px;
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  /* 或者用 justify-content: center; 居中 */
}
</style>