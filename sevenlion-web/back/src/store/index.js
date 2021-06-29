import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

// 全局变量
export default new Vuex.Store({
  state: {
    // 私有属性
    token: sessionStorage.getItem('token'),
    // userInfo: JSON.parse(sessionStorage.getItem('userInfo'))
    userInfo: JSON.parse(sessionStorage.getItem('userInfo')),
    menus:JSON.parse(sessionStorage.getItem('menus')),
    url: "http://localhost:7070"
  },
  mutations: {
    // set
    SET_TOKEN: (state, token) => {
      state.token = token
      // localStorage.setItem('token', token)
      sessionStorage.setItem('token', token)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    SET_MENUS: (state, menus) => {
      state.menus = menus
      sessionStorage.setItem('menus',JSON.stringify(menus))
    },
    REMOVE_INFO: (state) => {
      sessionStorage.clear()
    }
  },
  getters: {
    // get
    getToken: (state) => {
      return state.token
    },
    getMenus: (state) => {
      return state.menus
    },
    getUserInfo:(state) => {
      console.log(state.userInfo)
      return state.userInfo
    }

  },
  modules: {

  }
})
