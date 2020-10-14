<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>用 户 登 录</span>
    </div>
    <el-form
      align="center"
      ref="form"
      :model="user"
      label-width="0px"
      :label-position="labelPosition"
    >
      <el-form-item>
        <el-input v-model="user.name" placeholder="用 户 名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="user.pwd"
          type="password"
          placeholder="密 码"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 90%" @click="onSubmit"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { mapMutations } from "vuex";
export default {
  props: {
    isShow: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      labelPosition: "right",
      isLogin: true,
      user: {
        name: '',
        pwd: '',
      },
    };
  },
  methods: {
    ...mapMutations(["changeLogin"]),
    onSubmit() {
      let data = {
        name:this.user.name,
        password:this.user.pwd
      }
      let _this = this;
      this.$axios({
        url: "http://wxp520.top:88/api/user/user/login",
        method: "post",
        data: data,
      }).then(({ data }) => {
          console.log(data.access_token);
          _this.userToken = 'Bearer ' + data.access_token;
          _this.changeLogin({ Authorization: _this.userToken });
          _this.$router.push('/home');
          this.$message({
            showClose: true,
            message: "登录成功",
            type: "success",
          });
        }).catch((error) => {
          this.$message({
            showClose: true,
            message: "账号或密码错误",
            type: "error",
          });
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 400px;
}
</style>