<!-- src/components/LandFormDialog.vue -->
<template>
  <el-dialog
    :title="title"
    v-model="visible"
    width="600px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="internalForm"
      :rules="rules"
      label-width="100px"
      style="padding-right: 20px"
    >
      <el-form-item label="土地名称" prop="title">
        <el-input v-model="internalForm.title" placeholder="请输入土地名称" />
      </el-form-item>

      <el-form-item label="所属类目ID" prop="channel_uid">
        <el-input
          v-model="internalForm.channel_uid"
          placeholder="请输入类目ID"
          :disabled="props.mode === 'edit'"
        />
      </el-form-item>

      <el-form-item label="简介" prop="intro">
        <el-input
          v-model="internalForm.intro"
          type="textarea"
          :rows="2"
          placeholder="请输入简介"
        />
      </el-form-item>

      <el-form-item label="图片路径" prop="img">
        <el-input v-model="internalForm.img" placeholder="请输入图片URL" />
      </el-form-item>

      <el-form-item label="价格（元）" prop="price">
        <el-input-number
          v-model="internalForm.price"
          :min="0"
          :precision="2"
          controls-position="right"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="位置" prop="location">
        <el-input v-model="internalForm.location" placeholder="请输入位置信息" />
      </el-form-item>

      <el-form-item label="库存" prop="stock">
        <el-input-number
          v-model="internalForm.stock"
          :min="0"
          controls-position="right"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="规格" prop="norms">
        <el-input v-model="internalForm.norms" placeholder="例如：30平米/块" />
      </el-form-item>

      <el-form-item label="推荐首页" prop="recommendhome">
        <el-switch
          v-model="internalForm.recommendhome"
          :active-value="1"
          :inactive-value="0"
          active-text="是"
          inactive-text="否"
        />
      </el-form-item>

      <el-form-item label="详情" prop="details">
        <el-input
          v-model="internalForm.details"
          type="textarea"
          :rows="4"
          placeholder="请输入详细描述"
        />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="internalForm.remark"
          type="textarea"
          :rows="2"
          placeholder="可选备注"
        />
      </el-form-item>

      <el-form-item label="状态" prop="state">
        <el-radio-group v-model="internalForm.state">
          <el-radio :value="1">正常</el-radio>
          <el-radio :value="0">停用</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submit">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, watch,computed } from 'vue'
import { ElMessage } from 'element-plus'
import { addLand,updateLand } from '@/api/lands/lands'

const props = defineProps({
  modelValue: Boolean,
  mode: {
    type: String,
    default: 'add' // 'add' | 'edit'
  },
  data: {
    type: Object,
    default: () => ({})
  }
})

const emit = defineEmits(['update:modelValue', 'success'])

const visible = ref(false)
const formRef = ref(null)

// 表单初始结构
const emptyForm = () => ({
  uid: '',
  channel_uid: '',
  title: '',
  intro: '',
  img: '',
  price: 0,
  location: '',
  stock: 0,
  norms: '',
  recommendhome: 0,
  details: '',
  remark: '',
  state: 1,
  createdtime: '',
  updatedtime: ''
})

const internalForm = ref(emptyForm())

// 校验规则
const rules = computed(() => {
  const baseRules = {
    title: [{ required: true, message: '请输入土地名称', trigger: 'blur' }],
    price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
    stock: [{ required: true, message: '请输入库存', trigger: 'blur' }],
    location: [{ required: true, message: '请输入位置', trigger: 'blur' }]
  }

  // 只有在新增模式下，才要求 channel_uid 必填
  if (props.mode === 'add') {
    baseRules.channel_uid = [{ required: true, message: '请输入所属类目ID', trigger: 'blur' }]
  }

  return baseRules
})

// 同步外部 v-model
watch(
  () => props.modelValue,
  (val) => {
    visible.value = val
    if (val) {
      if (props.mode === 'edit' && props.data) {
        internalForm.value = { ...props.data }
      } else {
        internalForm.value = emptyForm()
        formRef.value?.resetFields()
      }
    }
  }
)

watch(visible, (val) => {
  emit('update:modelValue', val)
})

const submit = async () => {
  await formRef.value?.validate(async (valid) => {
    if (!valid) return ElMessage.warning('请填写必填项')

    try {
      if (props.mode === 'add') {
        await addLand(internalForm.value)
        ElMessage.success('新增成功')
      } else if (props.mode === 'edit') {
        // 注意：updateLand 需要 uid 作为路径参数，data 不包含 uid（或可包含，但后端以路径为准）
        const { uid, ...dataWithoutUid } = internalForm.value
        await updateLand(dataWithoutUid, uid)
        ElMessage.success('更新成功')
      }

      emit('success')
      visible.value = false
    } catch (err) {
      console.error(err)
      ElMessage.error(props.mode === 'add' ? '新增失败' : '更新失败')
    }
  })
}

const handleClose = () => {
  internalForm.value = emptyForm()
  formRef.value?.resetFields()
}
</script>