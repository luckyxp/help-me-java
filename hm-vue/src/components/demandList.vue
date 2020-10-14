<template>
  <div class="demandList">
    <el-collapse accordion>
      <el-collapse-item :name="index" v-for="(demand,index) in demands" :key="index">
        <h2 slot="title"><i class="el-icon-s-opportunity" />{{demand.title}} 赏金: {{demand.bounty}}</h2>
        <div>
          {{demand.content}}
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<script>
export default {
  data() {
    return {
      demands:[]
    };
  },
  methods: {
    list() {
      this.$axios({
        url: "http://wxp520.top:88/api/demand/demand/home_list",
        method: "get",
      }).then(({data}) => {
        console.log("主页需求",data.data.list);
        this.demands=data.data.list;
      });
    },
  },
  created(){
      this.list();
    }
};
</script>
<style scoped>
.demandList {
  /* margin-left: 200px; */
  width: 80%;
}
.el-collapse-item div {
  padding-left: 40px;
  text-align: left;
}
</style>