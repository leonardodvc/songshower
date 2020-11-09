import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index/Index'
import MobileIndex from '../views/MobileIndex/MobileIndex'

Vue.use(VueRouter)

const routes = [
  {
    path: '/:username',
    name: 'Index',
    component: Index,
    props: true,
  },
  {
    path: '/mobileIndex/:username',
    name: 'MobileIndex',
    component: MobileIndex,
    props: true,
  },
]

const router = new VueRouter({
  routes
})

export default router
