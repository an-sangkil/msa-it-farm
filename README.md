# 농장 관리 시스템 (Farm Management System)

Spring Boot 백엔드와 Vue.js 프론트엔드를 사용한 마이크로서비스 기반 농장 관리 시스템

## 🏗️ 프로젝트 구조

### 🖥️ 프론트엔드
- **front-client** : Vue CLI 기반 최신 웹 클라이언트 (포트 3000)
- **mas-farm-client** : 레거시 웹 클라이언트 (포트 8000)

### ⚙️ 백엔드 서비스
- **msa-farm-server** : 핵심 API 서비스
  - `msa-farm-server-api` : REST API 웹 서버 (포트 8090)
  - `msa-farm-server-model` : API 전용 도메인 모델
  - `msa-farm-server-repository` : 데이터 액세스 레파지토리
- **mas-farm-web-api** : 추가 웹 API 서비스

### 🔧 공통 모듈
- **msa-farm-common** : 공유 라이브러리
  - `msa-farm-common-security` : 보안 모듈
  - `mas-farm-common-domain` : 공통 도메인 모델

### 🌐 인프라
- **msa-farm-cloud-config** : Spring Cloud Config 서버 (포트 9000)
- **mas-farm-monitoring** : Spring Boot Admin 모니터링
- **docker** : MySQL 등 필요한 Docker 애플리케이션
- **configuration** : 외부 설정 파일
- **document** : 데이터베이스 스키마 및 참고 자료

## 🚀 빠른 시작

### 백엔드 실행
```bash
# 전체 프로젝트 빌드
mvn clean install -DskipTests

# API 서버 실행
cd msa-farm-server/msa-farm-server-api
mvn spring-boot:run
```

### 프론트엔드 실행

**최신 클라이언트 (권장):**
```bash
cd front-client
npm install
npm run local  # 로컬 환경 (포트 3000)
```

**레거시 클라이언트:**
```bash
cd mas-farm-client
npm install
npm run dev    # 개발 환경 (포트 8000)
```

## 🛠️ 기술 스택

### Backend
- **Framework:** Spring Boot 2.3.0 + Spring WebFlux
- **Database:** R2DBC (MySQL/H2)
- **Language:** Java 11-15
- **Build:** Maven
- **Architecture:** Microservices with Spring Cloud Config

### Frontend
- **Framework:** Vue.js 2.x
- **Build Tools:** Vue CLI 4.x / Webpack 3.x
- **UI:** Bootstrap Vue, TUI Grid
- **HTTP Client:** Axios
- **State Management:** Vuex

## 📊 서비스 포트

| 서비스 | 포트 | 설명 |
|--------|------|------|
| API Server | 8090 | 메인 REST API |
| Config Server | 9000 | Spring Cloud Config |
| Modern Frontend | 3000 | Vue CLI 클라이언트 |
| Legacy Frontend | 8000 | 레거시 웹 클라이언트 |

## 📝 문서

자세한 개발 가이드는 [CLAUDE.md](./CLAUDE.md) 파일을 참조하세요. 
