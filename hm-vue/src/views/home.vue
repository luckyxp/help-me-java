<template>
  <div id="home" align="center">
    <nav-top />
    <div class="center">
      <menu-card v-show="menuCardIsShow" />
      <demand-list v-show="demandListIsShow" />
      <info v-show="infoIsShow" />
      <pub-demand v-show="pubDeamndIsShow" />
      <my-pub v-show="myPubIsShow"/>
      <order-detail v-show="orderDetailIsShow"/>
    </div>
  </div>
</template>

<script>
import navTop from "../components/navTop";
import menuCard from "../components/menuCard";
import demandList from "../components/demandList";
import info from "../components/info";
import myPub from "../components/myPub"
import orderDetail from "../components/orderDetail"

import pubDemand from "../components/pubDemand";
export default {
  data() {
    return {};
  },
  components: {
    navTop,
    menuCard,
    demandList,
    info,
    pubDemand,
    myPub,
    orderDetail
  },
  computed: {
    demandListIsShow() {
      return this.$store.state.demandListIsShow;
    },
    infoIsShow() {
      return this.$store.state.infoIsShow;
    },
    menuCardIsShow() {
      return this.$store.state.menuCardIsShow;
    },
    pubDeamndIsShow() {
      return this.$store.state.pubDeamndIsShow;
    },
    myPubIsShow(){
      return this.$store.state.myPubIsShow
    },
    orderDetailIsShow(){
      return this.$store.state.orderDetailIsShow
    }
  },
};
</script>

<style scoped>
#home {
  background-color: white;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.center {
  /* background-color: aqua; */
  height: 100%;
  width: 1600px;
}
</style>