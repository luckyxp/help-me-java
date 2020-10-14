<template>
  <div class="myPub">
    <el-collapse accordion>
      <el-collapse-item
        :name="index1"
        v-for="(demand, index1) in demands"
        :key="index1"
      >
        <h2 slot="title">
          <i class="el-icon-s-opportunity" />{{
            demand.demandEntity.title
          }}
          赏金: {{ demand.demandEntity.bounty }}
          <el-tag :type="showTag(demand.demandEntity.demandStatus)">{{
            showTagText(demand.demandEntity.demandStatus)
          }}</el-tag>
        </h2>
        <div
          style="margin-top: 20px; font-size: 18px"
          v-for="(order, index2) in demand.orders"
          :key="index2"
        >
          <span>订单编号:#orid{{ order.id }},时间:{{ order.time }}</span>
          <el-tag @click="detail(index1, index2)">详情</el-tag>
        </div>
        <div v-if="demand.orders.length == 0">暂无人接您的需求</div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
export default {
  data() {
    return {
      demands: [],
    };
  },

  methods: {
    detail(demandIndex, orderIndex) {
      // let myPub = this.$store.state.myPub
      // console.log( myPub[demandIndex].orders[orderIndex]);
        
      this.$store.commit("setOrderDetailIndex", { "demandIndex":demandIndex, "orderIndex":orderIndex });
      this.$store.commit("changeTab", 5);

    },
    list() {
      this.$axios({
        url: "http://wxp520.top:88/api/demand/demand/my_pub",
        method: "get",
      }).then(({ data }) => {
        this.demands = data;
        this.$store.commit("setMyPub", data);
        console.log("我的发布", data);
      });
    },
    showTag(code) {
      if (code == 0) {
        return "";
      }
      if (code == 1) {
        return "warning";
      }
      if (code == 2) {
        return "success";
      }
      if (code == 3) {
        return "info";
      }
      if (code == 4) {
        return "info";
      }
      return "danger";
    },
    showTagText(code) {
      if (code == 0) {
        return "发布中";
      }
      if (code == 1) {
        return "完成中";
      }
      if (code == 2) {
        return "已完成";
      }
      if (code == 3) {
        return "已取消";
      }
      if (code == 4) {
        return "审核中";
      }
      return "未知";
    },
  },
  created() {
    this.list();
  },
};
</script>

<style scoped>
.myPub {
  /* margin-left: 200px; */
  width: 80%;
}
.el-collapse-item div {
  padding-left: 40px;
  text-align: left;
}
</style>