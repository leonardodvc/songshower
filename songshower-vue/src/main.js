import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './styles/base.scss'
import './styles/custom.scss'
import { IsMobile } from '@/utils/index';

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
	if (to.name === 'Index' && IsMobile()) {
		router.replace({ name: 'MobileIndex', params: { username: to.params.username }})
	} else {
		next()
	}
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
