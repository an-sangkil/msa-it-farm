# CLAUDE.md - Farm Client

이 파일은 Claude Code가 farm-client 코드베이스에서 작업할 때 참고하는 가이드라인입니다.

## 프로젝트 개요

**Farm Client**는 MSA 기반 농장 관리 시스템의 최신 프론트엔드 클라이언트입니다. Nuxt 4와 최신 웹 기술 스택을 사용하여 구축되었습니다.

## 기술 스택 상세

### 핵심 프레임워크
- **Nuxt 4.1.1**: 최신 풀스택 Vue.js 프레임워크
- **Vue 3.5.21**: Composition API 및 최신 리액티브 시스템
- **TypeScript 5.9.2**: 정적 타입 검사 및 개발자 경험 향상

### UI 및 스타일링
- **@nuxt/ui 3.3.3**: Tailwind CSS 기반 컴포넌트 라이브러리
- **Tailwind CSS**: Utility-first CSS 프레임워크 (자동 포함)
- **Headless UI**: 접근성 최적화된 무스타일 컴포넌트

### 개발 도구 및 유틸리티
- **@nuxt/content**: 마크다운 기반 콘텐츠 관리
- **@nuxt/image**: 자동 이미지 최적화 및 지연 로딩
- **@nuxt/scripts**: 외부 스크립트 최적화 로딩
- **@nuxt/test-utils**: Nuxt 전용 테스트 유틸리티
- **@nuxt/eslint**: Nuxt 최적화 린팅 규칙

## 개발 가이드라인

### 파일 구조
```
farm-client/
├── app/                    # 애플리케이션 소스코드
│   ├── components/         # Vue 컴포넌트
│   ├── pages/             # 라우트 기반 페이지
│   ├── layouts/           # 레이아웃 컴포넌트
│   ├── composables/       # 재사용 가능한 Composition API
│   ├── utils/             # 유틸리티 함수
│   ├── types/             # TypeScript 타입 정의
│   └── assets/            # 정적 자산
├── public/                # 공개 정적 파일
├── server/                # 서버 API 라우트
└── nuxt.config.ts         # Nuxt 설정
```

### 개발 명령어
```bash
# 개발 서버 실행 (권장: npm 사용)
npm run dev                # http://localhost:3000

# 프로덕션 빌드
npm run build

# 프로덕션 미리보기
npm run preview

# 정적 사이트 생성
npm run generate
```

### 코딩 스타일 및 규칙

#### Vue 컴포넌트
- **Composition API** 사용 (Options API 지양)
- **TypeScript**로 모든 컴포넌트 작성
- **Single File Components** (.vue) 형식
- **Script setup** 문법 권장

```vue
<script setup lang="ts">
// Composition API + TypeScript
import { ref, computed } from 'vue'

interface Props {
  title: string
  count?: number
}

const props = withDefaults(defineProps<Props>(), {
  count: 0
})
</script>
```

#### 스타일링 가이드
- **@nuxt/ui 컴포넌트** 우선 사용
- **Tailwind CSS 클래스** 조합으로 커스텀 스타일링
- **다크모드** 자동 지원 (`dark:` 접두사 활용)
- **반응형 디자인** (`sm:`, `md:`, `lg:` 접두사 활용)

```vue
<template>
  <UButton 
    color="primary" 
    variant="solid"
    class="bg-blue-500 hover:bg-blue-600 dark:bg-blue-400"
  >
    버튼
  </UButton>
</template>
```

#### 상태 관리
- **Pinia** 또는 **useState** (Nuxt 내장) 사용
- **Composables**로 비즈니스 로직 분리
- **Server State**는 @tanstack/vue-query 고려

### API 통신
- **$fetch** (Nuxt 내장) 또는 **axios** 사용
- **Server API Routes** (`server/api/`) 활용 고려
- **환경 변수**로 백엔드 엔드포인트 관리

```typescript
// composables/useApi.ts
export const useApi = () => {
  const baseURL = useRuntimeConfig().public.apiBase
  
  const get = async <T>(url: string): Promise<T> => {
    return await $fetch(`${baseURL}${url}`)
  }
  
  return { get }
}
```

### 성능 최적화
- **동적 임포트**로 코드 분할
- **@nuxt/image**로 이미지 최적화
- **SSR/SSG** 활용으로 초기 로딩 최적화
- **Lazy Loading** 적극 활용

## 백엔드 연동

### API 엔드포인트
- **msa-farm-server-api**: `http://localhost:8090` (개발)
- **Config Server**: `http://localhost:9000`

### 인증 및 보안
- **JWT 토큰** 기반 인증 예상
- **HTTP-only 쿠키** 또는 **localStorage** 토큰 저장
- **CORS 설정** 백엔드와 협의 필요

## 테스트 가이드라인

### 테스트 프레임워크
- **Vitest**: 단위 테스트 (Nuxt 권장)
- **@nuxt/test-utils**: Nuxt 특화 테스트 유틸리티
- **Playwright**: E2E 테스트 (필요시 추가)

```typescript
// tests/components/MyComponent.test.ts
import { describe, it, expect } from 'vitest'
import { mountSuspended } from '@nuxt/test-utils/runtime'
import MyComponent from '~/components/MyComponent.vue'

describe('MyComponent', () => {
  it('renders correctly', async () => {
    const component = await mountSuspended(MyComponent)
    expect(component.text()).toBe('Expected text')
  })
})
```

## 배포 및 환경 설정

### 환경 변수
```typescript
// nuxt.config.ts
export default defineNuxtConfig({
  runtimeConfig: {
    // Private keys (서버에서만 접근 가능)
    apiSecret: process.env.API_SECRET,
    
    public: {
      // Public keys (클라이언트에서 접근 가능)
      apiBase: process.env.API_BASE || 'http://localhost:8090'
    }
  }
})
```

### 빌드 최적화
- **Nitro** 엔진으로 다양한 플랫폼 배포 가능
- **Static Generation** 또는 **Server-Side Rendering** 선택
- **Cloudflare**, **Vercel**, **Netlify** 등 간편 배포

## 문제 해결

### 자주 발생하는 이슈
1. **TypeScript 오류**: `npm run dev` 재시작
2. **모듈 해결 실패**: `npm install` 재실행
3. **빌드 에러**: `.nuxt` 폴더 삭제 후 재빌드

### 디버깅
- **Nuxt DevTools** 활성화됨 (`devtools: { enabled: true }`)
- **Vue DevTools** 브라우저 확장 프로그램 사용
- **Console 로그** 및 **Network 탭** 적극 활용

## 코드 리뷰 체크리스트
- [ ] TypeScript 타입 안전성 확보
- [ ] @nuxt/ui 컴포넌트 우선 사용
- [ ] 접근성 (a11y) 고려
- [ ] 반응형 디자인 적용
- [ ] 성능 최적화 (lazy loading, 코드 분할)
- [ ] 다크모드 호환성
- [ ] ESLint 규칙 준수