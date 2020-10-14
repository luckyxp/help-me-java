<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    @select="handleSelect"
  >
    <el-submenu index="1">
      <el-avatar
        slot="title"
        shape="square"
        :size="50"
        src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
      ></el-avatar>
      <el-menu-item index="1-1">账号: {{info.name}}</el-menu-item>
      <el-menu-item index="1-2">昵称: {{info.fullName}}</el-menu-item>
      <el-menu-item index="1-3">电话: {{info.phone}}</el-menu-item>
      <el-menu-item index="1-4">余额: {{info.moeny}}</el-menu-item>
      <el-menu-item index="1-5">退出登录</el-menu-item>
    </el-submenu>
    <el-menu-item index="2">发布需求</el-menu-item>
    <el-menu-item index="3">订单中心</el-menu-item>
    <el-menu-item index="4">需求大厅</el-menu-item>
    <el-menu-item index="5">我的发布</el-menu-item>
    <!-- <el-menu-item index="5">首页</el-menu-item> -->
  </el-menu>
</template>
<script>
export default {
  data() {
    return {
      activeIndex: "4",
      info:{}
    };
  },
  methods: {
    getInfo() {
      this.$axios({
        url: "http://wxp520.top:88/api/user/user/info",
        method: "get",
      }).then(({ data }) => {
        console.log(data);
        this.info = data;
      });
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      if (key == "1-1"||key == "1-2"||key == "1-3"||key == "1-4") {
        this.$store.commit("changeTab", 0);
      }
      if (key == "1-5") {
        localStorage.removeItem("Authorization");
        this.$router.push("/login");
      }
      if (key == 2) {
        this.$store.commit("changeTab", 3);
      }
      if (key == 4) {
        this.$store.commit("changeTab", 1);
      }
      if (key == 5) {
        this.$store.commit("changeTab", 4);
      }
      if (key == 6) {
        this.$store.commit("changeTab", 2);
      }

    },
  },
  created(){
    this.getInfo();
  }
};
</script>
<style scoped>
.el-avatar {
  margin-right: 5px;
  margin-top: 5px;
  margin-left: 80px;
}
.el-avatar,
.el-menu-item,
.el-submenu {
  float: right;
}
</style>