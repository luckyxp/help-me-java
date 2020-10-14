<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="time">
      <el-input v-model="dataForm.time" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="note">
      <el-input v-model="dataForm.note" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="status">
      <el-input v-model="dataForm.status" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="images">
      <el-input v-model="dataForm.images" placeholder=""></el-input>
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
          time: '',
          note: '',
          status: '',
          orderId: '',
          images: ''
        },
        dataRule: {
          time: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          images: [
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
              url: this.$http.adornUrl(`/order/orderresult/info/${this.dataForm.id}`),
              method: 'get',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.time = data.orderResult.time
                this.dataForm.note = data.orderResult.note
                this.dataForm.status = data.orderResult.status
                this.dataForm.orderId = data.orderResult.orderId
                this.dataForm.images = data.orderResult.images
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
              url: this.$http.adornUrl(`/order/orderresult/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'time': this.dataForm.time,
                'note': this.dataForm.note,
                'status': this.dataForm.status,
                'orderId': this.dataForm.orderId,
                'images': this.dataForm.images
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
