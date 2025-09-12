# Farm Client - Nuxt 4 기반 농장 관리 시스템

MSA 기반 농장 관리 시스템의 프론트엔드 클라이언트입니다. Nuxt 4와 @nuxt/ui (Tailwind CSS 기반)를 사용하여 구축되었습니다.

## 🚀 기술 스택

- **Framework**: Nuxt 4.1.1 (Vue 3 + TypeScript)
- **UI Library**: @nuxt/ui 3.3.3 (Tailwind CSS 기반)
- **Content**: @nuxt/content (마크다운 지원)
- **Image**: @nuxt/image (이미지 최적화)
- **Testing**: @nuxt/test-utils
- **Linting**: @nuxt/eslint

## 📦 설치 및 설정

의존성 패키지를 설치합니다:

```bash
# npm
npm install

# pnpm
pnpm install

# yarn
yarn install
```

## 🛠️ 개발 서버 실행

개발 서버를 `http://localhost:3000`에서 시작합니다:

```bash
# npm
npm run dev

# pnpm
pnpm dev

# yarn
yarn dev
```

## 🏗️ 프로덕션 빌드

프로덕션용 애플리케이션을 빌드합니다:

```bash
# npm
npm run build

# pnpm
pnpm build

# yarn
yarn build
```

빌드된 애플리케이션을 로컬에서 미리보기:

```bash
# npm
npm run preview

# pnpm
pnpm preview

# yarn
yarn preview
```

## 📁 프로젝트 구조

```
farm-client/
├── app/                    # Nuxt 4 애플리케이션 코드
├── public/                 # 정적 파일
├── .nuxt/                  # Nuxt 빌드 아티팩트
├── nuxt.config.ts         # Nuxt 설정 파일
├── package.json           # 프로젝트 의존성
├── tsconfig.json          # TypeScript 설정
└── eslint.config.mjs      # ESLint 설정
```

## 🎨 UI 컴포넌트

이 프로젝트는 @nuxt/ui 3.3.3을 사용하여 일관된 디자인 시스템을 제공합니다:

- Tailwind CSS 기반의 최신 컴포넌트
- 다크모드 자동 지원
- 접근성(Accessibility) 최적화
- TypeScript 완벽 지원

## 📚 관련 문서

- [Nuxt 4 문서](https://nuxt.com/docs/getting-started/introduction)
- [Nuxt UI 문서](https://ui.nuxt.com/)
- [Tailwind CSS 문서](https://tailwindcss.com/docs)
- [배포 가이드](https://nuxt.com/docs/getting-started/deployment)

## 🔗 관련 프로젝트

- `msa-farm-server/`: Spring Boot 기반 백엔드 API
- `front-client/`: Vue CLI 기반 기존 클라이언트
- `mas-farm-client/`: 레거시 Vue 2 클라이언트
