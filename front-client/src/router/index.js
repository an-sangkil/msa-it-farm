import Vue from 'vue'
import VueRouter from 'vue-router'

import CattleList from '../views/cattle/CattleList'
import CattleForm from '../views/cattle/CattleForm'
import CattleView from '../views/cattle/CattleView'
import DiaryList from '../views/diary/DiaryList'
import DiaryForm from '../views/diary/DiaryForm'
import DiaryView from '../views/diary/DiaryView'

// Views - Pages
import NotFound from '../components/common/NotFound'

// Containers
const TheContainer = () => import('../containers/TheContainer')

// Views
const Dashboard = () => import('../views/Dashboard')

const Page404 = () => import('../views/pages/Page404')
const Page500 = () => import('../views/pages/Page500')
const Login = () => import('../views/pages/Login')
const Register = () => import('../views/pages/Register')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'LoginPage',
    redirect: '/pages/login'
  },
  {
    path: '*',
    component: NotFound
  },
  {
    path: '/dashboard',
    name: 'HomePage',
    redirect: '/dashboard/dashboardView',
    component: TheContainer,
    children: [
      {
        path: 'dashboardView',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: '/cattle',
        redirect: '/cattle/cattleList',
        name: 'Cattle',
        component: {
          render (c) {
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
          },
          {
            path: 'cattleDetail',
            name: 'CattleView',
            component: CattleView
          }
        ]
      },
      {
        path: '/diary',
        redirect: '/diary/diaryList',
        name: 'Diary',
        component: {
          render (c) {
            return c('router-view')
          }
        },
        children: [
          {
            path: 'diaryList',
            name: 'DiaryList',
            component: DiaryList
          },
          {
            path: 'diaryForm',
            name: 'DiaryForm',
            component: DiaryForm
          },
          {
            path: 'diaryDetail',
            name: 'DiaryView',
            component: DiaryView
          }
        ]
      },
      {
        path: 'base',
        redirect: '/base/cards',
        name: 'Base',
        component: {
          render (c) {
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
      render (c) {
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
  scrollBehavior: () => ({ y: 0 }),
  routes
})

// router.beforeEach(async (to, from, next)=>{
// alert('로그인 해주세요');
// return next('/login');
// })

export default router
