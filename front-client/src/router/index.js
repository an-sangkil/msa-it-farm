import { createRouter, createWebHistory } from 'vue-router'

// 기본 페이지들 (외부 라이브러리 의존성 없음)
import NotFound from '../components/common/NotFound.vue'

// 동적 import로 일부 페이지들만 로드
const Login = () => import('../views/pages/Login.vue')
const Page404 = () => import('../views/pages/Page404.vue')

// 간단한 테스트 컴포넌트들
const Dashboard = { 
  template: `
    <div class="container mt-4">
      <h1>Dashboard</h1>
      <p>Welcome to MSA IT Farm Dashboard!</p>
      <div class="row">
        <div class="col-md-4">
          <div class="card">
            <div class="card-header">소 관리</div>
            <div class="card-body">
              <router-link to="/cattle" class="btn btn-primary">소 목록 보기</router-link>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <div class="card-header">일지 관리</div>
            <div class="card-body">
              <router-link to="/diary" class="btn btn-primary">일지 보기</router-link>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <div class="card-header">관찰 기록</div>
            <div class="card-body">
              <router-link to="/observe" class="btn btn-primary">관찰 기록</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  `
}

const SimpleContainer = {
  template: `
    <div class="d-flex flex-column min-vh-100">
      <!-- Simple Header -->
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
          <router-link class="navbar-brand" to="/dashboard">MSA IT Farm</router-link>
          <div class="navbar-nav ml-auto">
            <router-link class="nav-link" to="/dashboard">Dashboard</router-link>
            <router-link class="nav-link" to="/cattle">소 관리</router-link>
            <router-link class="nav-link" to="/diary">일지</router-link>
            <router-link class="nav-link" to="/observe">관찰</router-link>
            <router-link class="nav-link" to="/pages/login">로그아웃</router-link>
          </div>
        </div>
      </nav>
      
      <!-- Main Content -->
      <main class="flex-grow-1">
        <router-view></router-view>
      </main>
      
      <!-- Simple Footer -->
      <footer class="bg-light py-3 mt-4">
        <div class="container text-center">
          <small>&copy; 2024 MSA IT Farm - Vue 3 버전</small>
        </div>
      </footer>
    </div>
  `
}

const CattleHome = { 
  template: `
    <div class="container mt-4">
      <h2>소 관리 시스템</h2>
      <p>소 관리 관련 기능들이 여기에 표시됩니다.</p>
      <div class="alert alert-info">
        <strong>알림:</strong> 현재 Vue 3 업그레이드 중입니다. 일부 기능은 임시적으로 비활성화되어 있습니다.
      </div>
      <router-view></router-view>
    </div>
  `
}

const DiaryHome = { 
  template: `
    <div class="container mt-4">
      <h2>농장 일지</h2>
      <p>농장 일지 관련 기능들이 여기에 표시됩니다.</p>
      <router-view></router-view>
    </div>
  `
}

const ObserveHome = { 
  template: `
    <div class="container mt-4">
      <h2>관찰 기록</h2>
      <p>관찰 기록 관련 기능들이 여기에 표시됩니다.</p>
      <router-view></router-view>
    </div>
  `
}

/**
 * 간단한 인증 체크
 */
const requireAuth = () => {
  console.log('🔐 requireAuth: 인증 확인')
  return true // 임시로 모든 접근 허용
}

const rejectAuth = () => {
  console.log('🔐 rejectAuth: 로그인 페이지 접근')
  return true
}

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/pages/login'
  },
  {
    path: '/login',
    name: 'LoginDirect',
    redirect: '/pages/login'
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    beforeEnter: requireAuth,
    component: SimpleContainer,
    children: [
      {
        path: '',
        name: 'DashboardHome',
        component: Dashboard
      }
    ]
  },
  {
    path: '/cattle',
    name: 'Cattle',
    beforeEnter: requireAuth,
    component: SimpleContainer,
    children: [
      {
        path: '',
        name: 'CattleHome',
        component: CattleHome
      }
    ]
  },
  {
    path: '/diary',
    name: 'Diary',
    beforeEnter: requireAuth,
    component: SimpleContainer,
    children: [
      {
        path: '',
        name: 'DiaryHome',
        component: DiaryHome
      }
    ]
  },
  {
    path: '/observe',
    name: 'Observe',
    beforeEnter: requireAuth,
    component: SimpleContainer,
    children: [
      {
        path: '',
        name: 'ObserveHome',
        component: ObserveHome
      }
    ]
  },
  {
    path: '/pages',
    name: 'Pages',
    component: { template: '<router-view />' },
    children: [
      {
        path: 'login',
        name: 'Login',
        component: Login,
        beforeEnter: rejectAuth
      },
      {
        path: '404',
        name: 'Page404',
        component: Page404
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  linkActiveClass: 'active',
  scrollBehavior: () => ({ top: 0 }),
  routes
})

router.beforeEach(async (to, from) => {
  console.log('🔄 Router navigating from', from.path, 'to', to.path)
  return true
})

export default router