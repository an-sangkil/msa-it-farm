import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Template from '@/components/Template'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [

    {
      path: '/',
      name: 'main',
      component: Template
    },
    {
      path: '/hello2',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})

