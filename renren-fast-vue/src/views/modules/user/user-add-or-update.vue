<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="name">
      <el-input v-model="dataForm.name" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="password">
      <el-input v-model="dataForm.password" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="fullName">
      <el-input v-model="dataForm.fullName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="headImage">
      <el-input v-model="dataForm.headImage" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="phone">
      <el-input v-model="dataForm.phone" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="moeny">
      <el-input v-model="dataForm.moeny" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="authorities">
      <el-input v-model="dataForm.authorities" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          password: '',
          fullName: '',
          headImage: '',
          phone: '',
          moeny: '',
          authorities: ''
        },
        dataRule: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          fullName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          headImage: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          moeny: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          authorities: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/user/user/info/${this.dataForm.id}`),
              method: 'get',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.user.name
                this.dataForm.password = data.user.password
                this.dataForm.fullName = data.user.fullName
                this.dataForm.headImage = data.user.headImage
                this.dataForm.phone = data.user.phone
                this.dataForm.moeny = data.user.moeny
                this.dataForm.authorities = data.user.authorities
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/user/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'password': this.dataForm.password,
                'fullName': this.dataForm.fullName,
                'headImage': this.dataForm.headImage,
                'phone': this.dataForm.phone,
                'moeny': this.dataForm.moeny,
                'authorities': this.dataForm.authorities
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
