import Vue from 'vue'
import VueRouter from 'vue-router'

import CattleList from '../views/cattle/CattleList'
import CattleForm from '../views/cattle/CattleForm'
import CattleView from '../views/cattle/CattleView'
import DiaryList from '../views/diary/DiaryList'
import DiaryForm from '../views/diary/DiaryForm'
import DiaryView from '../views/diary/DiaryView'
import ObserveList from '../views/observe/Observe'

// Views - Pages
import NotFound from '../components/common/NotFound'
import Store from '../store'

// Containers
const TheContainer = () => import('../containers/TheContainer')

// Views
const Dashboard = () => import('../views/Dashboard')

const Page404 = () => import('../views/pages/Page404')
const Page500 = () => import('../views/pages/Page500')
const Login = () => import('../views/pages/Login')
const Register = () => import('../views/pages/Register')

Vue.use(VueRouter)

/**
 * 이미 로그인된 사용자 체크
 * @param to
 * @param from
 * @param next
 */
const rejectAuth = (to, from, next) => {
  console.log('rejectAuth  Store.getters.isAuth : ', Store.getters.isAuth)
  // if (Store.getters.isAuth === true) {
  //   alert('이미 로그인 되었습니다.')
    next()
  // } else {
  //   // 로그인 페이지로 이동
  //   next('/');
  // }
}

/**
 * 사용자 정보 체크
 * @param to
 * @param from
 * @param next
 */
const requireAuth = (to, from, next) => {

  console.log('requireAuth  Store.getters.isAuth : ', Store.getters.isAuth)

  // if (Store.getters.isAuth === false) {
  //   alert("로그인 필요한 기능 입니다.")
  //   next('/')
  // } else {
    next()
  // }
}

const routes = [
  {
    path: '/',
    name: 'LoginPage',
    redirect: '/pages/login',
    beforeEnter: rejectAuth,
  },
  {
    path: '*',
    component: NotFound
  },
  {
    path: '/dashboard',
    name: 'HomePage',
    redirect: '/dashboard/dashboardView',
    beforeEnter: requireAuth,
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
          render(c) {
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
        path: '/observe',
        redirect: '/observe/observeList',
        name: 'Observe',
        component: {
          render(c) {
            return c('router-view')
          }
        },
        children: [
          {
            path: 'observeList',
            name: 'ObserveList',
            component: ObserveList
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
        component: Login,
        beforEnter: rejectAuth
      },
      {
        path: 'register',
        name: 'Register',
        component: Register
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,

  // 추가분
  linkActiveClass: 'active',
  scrollBehavior: () => ({y: 0}),
  routes
})

router.beforeEach(async (to, from, next) => {
  next()
})

export default router
