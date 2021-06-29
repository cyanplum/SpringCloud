import axios from 'axios'
import Element from 'element-ui'
import router from './router'


axios.defaults.baseURL = 'http://localhost:8609'

// axios的前置拦截
axios.interceptors.request.use(config => {
  // config.headers['Authorization'] = 'Bearer '+ indexStore.getters.getToken
  return config
})

// axios的后置拦截
axios.interceptors.response.use(response => {
  let res = response.data
  if (res.code === 401) {
    // indexStore.commit('REMOVE_INFO')
    router.push('/login')
    this.reload()
  }
  if (res.success) {
    return response
  } else {
    Element.Message.error(res.msg, {duration: 2000})

    //结束请求，防止进入axios掉用里面
    return Promise.reject(res.msg)
  }
},
  error => {
  // 可以针对返回的状态码进行页面的跳转等操作
    Element.Message.error(error.message, {duration: 2000})
    return Promise.reject(error)
  }
)
