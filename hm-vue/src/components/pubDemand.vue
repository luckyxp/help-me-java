<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <h2>发布需求</h2>
    </div>
    <el-form ref="form" :model="demand" label-width="80px">
      <el-form-item label="需求标题">
        <el-input v-model="demand.title"></el-input>
      </el-form-item>
      <el-form-item label="需求内容">
        <el-input v-model="demand.content"></el-input>
      </el-form-item>
      <el-form-item label="赏金">
        <el-input v-model="demand.bounty"></el-input>
      </el-form-item>
      <el-button type="primary" @click="pubDemand" class="subButton"
        >发布</el-button
      >
    </el-form>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      demand: {
        title: "",
        content: "",
        bounty: 0,
      },
    };
  },

  methods: {
    pubDemand() {
      let data = {
        title: this.demand.title,
        content: this.demand.content,
        bounty: this.demand.bounty,
        imges:"default.png"
      };
      this.$axios({
        url: "http://wxp520.top:88/api/demand/demand/pub_demand",
        method: "post",
        data: data,
      }).then(() => {
        this.$notify({
          title: "发布成功",
          message: this.demand.title,
          type: "success",
        });
      });
    },
  },
};
</script>

<style scoped>
.box-card {
  width: 40%;
}
.subButton {
  float: right;
  margin-bottom: 20px;
  margin-right: 30px;
}
</style>