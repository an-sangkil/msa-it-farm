import Vue from 'vue'
import VueRouter from 'vue-router'

// Containers
const TheContainer = () => import('../containers/TheContainer')

// Views
const Dashboard = () => import('../views/Dashboard')
import CattleList from '../views/cattle/CattleList'
import CattleSave from '../views/cattle/CattleSave'

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
    name: 'HomePage',
    redirect: "/pages/login"
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '*'
    , component: NotFound
  }
  ,
  {
    path: '/dashboard',
    name: 'Home',
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
            path: 'cattleSave',
            name: 'CattleSave',
            component: CattleSave
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

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,

  // 추가분
  linkActiveClass: 'active',
  scrollBehavior: () => ({y: 0}),
  routes
})

export default router
