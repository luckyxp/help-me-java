import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    "infoIsShow": false,
    "demandListIsShow": true,
    "menuCardIsShow": false,
    "pubDeamndIsShow": false,
    "myPubIsShow": false,
    "orderDetailIsShow": false,
    "myPub": [],
    "orderDetailIndex": { "demandIndex": 0, "orderIndex": 0 }
  },
  mutations: {
    changeLogin(state, user) {
      state.Authorization = user.Authorization;
      localStorage.setItem('Authorization', user.Authorization);
    },
    setOrderDetailIndex(state, orderDetailIndex) {
      state.orderDetailIndex = orderDetailIndex
    },
    setMyPub(state, myPub) {
      state.myPub = myPub
    },
    changeTab(state, index) {
      state.infoIsShow = false;
      state.demandListIsShow = false;
      state.menuCardIsShow = false;
      state.pubDeamndIsShow = false;
      state.myPubIsShow = false;
      state.orderDetailIsShow = false;
      if (index == 0) {
        state.infoIsShow = true
      }
      if (index == 1) {
        state.demandListIsShow = true
      }
      if (index == 2) {
        state.menuCardIsShow = true
      }
      if (index == 3) {
        state.pubDeamndIsShow = true
      }
      if (index == 4) {
        state.myPubIsShow = true
      }
      if (index == 5) {
        state.orderDetailIsShow = true
      }
    }
  },
  actions: {
  },
  modules: {
  }
})
