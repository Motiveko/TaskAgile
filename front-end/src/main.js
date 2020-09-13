import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'

Vue.config.productionTip = false

Vue.use(Vuelidate)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

// axios 부트스트랩
// 모든 요청에 /api붙이지 않아도 axios를 통해 백앤드로 가는곳에 /api붙여준다.
axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json' // 응답은 json으로만 받는다.
// 에러를 전파하기 위해 interceptor 설정!
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)
