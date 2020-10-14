<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="title">
      <el-input v-model="dataForm.title" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="content">
      <el-input v-model="dataForm.content" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="imges">
      <el-input v-model="dataForm.imges" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="pubId">
      <el-input v-model="dataForm.pubId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="bounty">
      <el-input v-model="dataForm.bounty" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="demandStatus">
      <el-input v-model="dataForm.demandStatus" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="time">
      <el-input v-model="dataForm.time" placeholder=""></el-input>
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
          title: '',
          content: '',
          imges: '',
          pubId: '',
          bounty: '',
          demandStatus: '',
          time: ''
        },
        dataRule: {
          title: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          imges: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          pubId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          bounty: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          demandStatus: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          time: [
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
              url: this.$http.adornUrl(`/demand/demand/info/${this.dataForm.id}`),
              method: 'get',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.title = data.demand.title
                this.dataForm.content = data.demand.content
                this.dataForm.imges = data.demand.imges
                this.dataForm.pubId = data.demand.pubId
                this.dataForm.bounty = data.demand.bounty
                this.dataForm.demandStatus = data.demand.demandStatus
                this.dataForm.time = data.demand.time
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
              url: this.$http.adornUrl(`/demand/demand/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              headers:{
            "Authorization":"Bearer "+this.$cookie.get("access_token")
            },
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'title': this.dataForm.title,
                'content': this.dataForm.content,
                'imges': this.dataForm.imges,
                'pubId': this.dataForm.pubId,
                'bounty': this.dataForm.bounty,
                'demandStatus': this.dataForm.demandStatus,
                'time': this.dataForm.time
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
