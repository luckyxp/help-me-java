import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
// import LoginForm from '../components/loginForm.vue'
import Home from '../views/home.vue'
// import menuCard from '../components/menuCard.vue'
import DemandList from '../components/demandList.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },{
    path: '/login',
    name: 'Login',
    component: Login
  },{
    path:'/home',
    name: 'Home',
    component: Home
  },{
    path:'/demandList',
    name: 'DemandList',
    component: DemandList
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to,from,next)=>{
  if(to.path==='/login'){
    next()
  }else{
    let token = localStorage.getItem('Authorization')
    if(token===null||token===''){
      next('/login')
    }else{
      next()
    }
  }
})
export default router
