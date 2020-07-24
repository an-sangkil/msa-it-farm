import Vue from 'vue'
import VueRouter from 'vue-router'

// Containers
const TheContainer = () => import('../containers/TheContainer')

// Views
const Dashboard = () => import('../views/Dashboard')

import CattleList from '../views/cattle/CattleList'
import CattleForm from '../views/cattle/CattleForm'
import ScheduleList from '../views/schedule/ScheduleList'
import ScheduleForm from '../views/schedule/ScheduleForm'

// Views - Pages
import NotFound from "../components/common/NotFound";

const Page404 = () => import('../views/pages/Page404')
const Page500 = () => import('../views/pages/Page500')
const Login = () => import('../views/pages/Login')
const Register = () => import('../views/pages/Register')


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'LoginPage',
    redirect: "/pages/login"
  },
  {
    path: '*'
    , component: NotFound
  }
  ,
  {
    path: '/dashboard',
    name: 'HomePage',
    component: TheContainer,
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: '/cattle',
        redirect: '/cattle/cattleList',
        name: 'Theme',
        component: {
          render(c) {
            return c('router-view')
          }
        },
        children: [
          {
            path: 'cattleList',
            name: 'CattleList',
            component: CattleList
          },
          {
            path: 'cattleForm',
            name: 'CattleForm',
            component: CattleForm
          }
        ]
      },
      {
        path: '/schedule',
        redirect: '/schedule/scheduleList',
        name: 'Base',
        component: {
          render(c) {
            return c('router-view')
          }
        },
        children: [
          {
            path: 'scheduleList',
            name: 'ScheduleList',
            component: ScheduleList
          },
          {
            path: 'scheduleForm',
            name: 'ScheduleForm',
            component: ScheduleForm
          }
        ]
      },
      {
        path: 'base',
        redirect: '/base/cards',
        name: 'Base',
        component: {
          render(c) {
            return c('router-view')
          }
        }
      }
    ]
  },
  {
    path: '/pages',
    redirect: '/pages/404',
    name: 'Pages',
    component: {
      render(c) {
        return c('router-view')
      }
    },
    children: [
      {
        path: '404',
        name: 'Page404',
        component: Page404
      },
      {
        path: '500',
        name: 'Page500',
        component: Page500
      },
      {
        path: 'login',
        name: 'Login',
        component: Login
      },
      {
        path: 'register',
        name: 'Register',
        component: Register
      }
    ]
  }
]

const requireAuth = () => (from, to, next) => {

}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,

  // 추가분
  linkActiveClass: 'active',
  scrollBehavior: () => ({y: 0}),
  routes
})

//router.beforeEach(async (to, from, next)=>{
//alert('로그인 해주세요');
//return next('/login');
//})

export default router
