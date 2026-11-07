<template>
  <div class="card pwd-card">
    <div class="pwd-head">
      <div class="pwd-title">修改登录密码</div>
      <div class="pwd-desc">为了账号安全，建议定期更换密码；修改成功后需要重新登录。</div>
    </div>

    <el-form ref="formRef" :model="form" :rules="rules" label-width="104px" class="pwd-form">
      <el-form-item label="原密码" prop="password">
        <el-input v-model="form.password" type="password" show-password placeholder="请输入原密码" />
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="form.newPassword" type="password" show-password placeholder="请输入新密码" />
      </el-form-item>
      <el-form-item label="确认新密码" prop="confirm">
        <el-input v-model="form.confirm" type="password" show-password placeholder="请再次输入新密码"
                  @keyup.enter="submit" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">确认修改</el-button>
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'
import { getUser, clearUser } from '@/utils/auth'

const router = useRouter()
const formRef = ref()

const form = reactive({
  password: '',
  newPassword: '',
  confirm: ''
})

const validateConfirm = (rule, value, callback) => {
  if (value !== form.newPassword) {
    callback(new Error('两次输入的新密码不一致'))
  } else {
    callback()
  }
}

const validateNew = (rule, value, callback) => {
  if (value && value === form.password) {
    callback(new Error('新密码不能和原密码相同'))
  } else {
    callback()
  }
}

const rules = {
  password: [{ required: true, message: '请输入原密码', trigger: 'blur' }],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { validator: validateNew, trigger: 'blur' }
  ],
  confirm: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { validator: validateConfirm, trigger: 'blur' }
  ]
}

const submit = async () => {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return

  const res = await request.put('/admin/updatePassword', {
    username: getUser().username,
    password: form.password,
    newPassword: form.newPassword
  })

  if (res.code !== '200') {
    ElMessage.error(res.msg || '修改失败')
    return
  }

  ElMessage.success('密码已修改，请重新登录')
  clearUser()
  router.push('/login')
}

const reset = () => {
  form.password = ''
  form.newPassword = ''
  form.confirm = ''
  formRef.value?.clearValidate()
}
</script>

<style scoped>
.pwd-card {
  padding: 26px 30px;
  max-width: 720px;
}

.pwd-head {
  margin-bottom: 22px;
  padding-bottom: 16px;
  border-bottom: 1px solid var(--line);
}

.pwd-title {
  font-size: 15px;
  font-weight: 600;
  color: var(--text);
}

.pwd-desc {
  margin-top: 6px;
  font-size: 13px;
  color: var(--text-3);
}

.pwd-form {
  max-width: 460px;
}
</style>
