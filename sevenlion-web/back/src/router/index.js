import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Index from '@/views/Index'
import Store from '../store/index'
Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: {name: 'index'}
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children:[
      ]
    },
  ],
  mode: 'history'
})
//挂载路由导航权限
router.beforeEach((to, from, next) => {
  // to 访问路径 from从哪个路径跳转过来
  // next是一个函数，表示直接放行 next() next('/xx')
  if (to.path === '/login') {
    return next()
  }
  const token = Store.getters.getToken
  //如果没有token进行跳转到/login
  if (token == null) {
    return next('/login')
  }
  return next()
})

export default router
