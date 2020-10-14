<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <h2>
        个人信息
        <el-button
          style="float: right; padding: 3px 0"
          type="text"
          @click="changeInfo"
          v-show="showButton"
          >修改信息</el-button
        >
        <el-button
          type="primary"
          v-show="!showButton"
          @click="subChange"
          class="subButton"
          >提交修改</el-button
        >
      </h2>
    </div>
    <el-form ref="form" :model="info" label-width="80px">
      <el-form-item label="账号:">
        <el-input v-model="info.name" disabled></el-input>
      </el-form-item>
      <el-form-item label="昵称:">
        <el-input v-model="info.fullName" :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="电话:">
        <el-input v-model="info.phone" :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="账户余额:">
        <el-input v-model="info.moeny" disabled></el-input>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      showButton: true,
      info: {},
      isDisabled: true,
    };
  },
  methods: {
    changeInfo() {
      this.isDisabled = false;
      this.showButton = false;
    },
    subChange() {
      this.isDisabled = true;
      this.showButton = true;
      let data = {
        phone:this.info.phone,
        fullName:this.info.fullName,
      }
      this.$axios({
        url: "http://wxp520.top:88/api/user/user/change_info",
        method: "post",
        data:data
      }).then(()=>{
        this.$notify({
          title: "信息修改成功!",
          message: "昵称: "+this.info.fullName +" 手机: "+this.info.phone,
          type: "success",
        });
      })
    },
    getInfo() {
      this.$axios({
        url: "http://wxp520.top:88/api/user/user/info",
        method: "get",
      }).then(({ data }) => {
        this.info = data;
      });
    },
  },
  created() {
    this.getInfo();
  },
};
</script>

<style scoped>
.k {
  text-align: right;
  font-size: 21px;
}
.v {
  padding-left: 20px;
  text-align: left;
  font-size: 21px;
}
.el-row {
  margin-top: 10px;
}
.box-card {
  width: 40%;
}
.subButton {
  margin-left: 350px;
}
</style>