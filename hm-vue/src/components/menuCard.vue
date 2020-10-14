<template>
  <div class="card">
    <el-row>
      <el-col :span="12">
        <el-card shadow="hover" :body-style="{ padding: '0px' }">
          <img src="../assets/demand.png" class="image" />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" :body-style="{ padding: '0px' }">
          <img src="../assets/menuCardImg.jpg" class="image" />
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-card shadow="hover" :body-style="{ padding: '0px' }">
          <img src="../assets/menuCardImg.jpg" class="image" />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" :body-style="{ padding: '0px' }">
          <img src="../assets/menuCardImg.jpg" class="image" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.card {
  /* margin-top: 240px; */
  text-align: center;
  width: 900px;
  /* border: 1px solid red; */
}
.el-col {
  padding: 30px;
}
.el-card {
  /* border: 1px solid; */
  /* width: 230px; */
  height: 200px;
  border-radius: 30px;
}
</style>