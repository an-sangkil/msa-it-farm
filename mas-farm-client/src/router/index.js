import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Template from '@/components/Template'
import NotFound from "../components/common/NotFound";
import Main from "../components/main/Main";
import InsideVue from "../components/InsideVue"
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [

    {
      path: '/',
      name: 'main',
      components: {
        default:Main,
      }
    },
    {
      path: '/main',
      name: 'body',
      components: {
        default:Main,
        insideVue:InsideVue
      }
    },
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '*'
      , component: NotFound
    }
  ]
})

