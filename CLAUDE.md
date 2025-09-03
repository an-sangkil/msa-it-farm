# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요

Spring Boot 백엔드와 Vue.js 프론트엔드를 사용한 마이크로서비스 기반 농장 관리 시스템입니다.

### 아키텍처 구조

**마이크로서비스 구성요소:**
- `msa-farm-server/`: 리액티브 Spring Boot 기반 핵심 API 서비스
  - `msa-farm-server-api/`: 메인 REST API 서비스 (포트 8090)
  - `msa-farm-server-model/`: 도메인 모델 및 엔티티
  - `msa-farm-server-repository/`: 데이터 액세스 계층
- `msa-farm-common/`: 공유 모듈
  - `msa-farm-common-security/`: 보안 유틸리티
  - `mas-farm-common-domain/`: 공통 도메인 객체
- `msa-farm-cloud-config/`: Spring Cloud Config 서버 (포트 9000)
- `mas-farm-monitoring/`: Spring Boot Admin 모니터링
- `mas-farm-web-api/`: 추가 웹 API 서비스

**프론트엔드 애플리케이션:**
- `front-client/`: Vue CLI 기반 최신 Vue 2 클라이언트 (포트 3000)
- `mas-farm-client/`: Webpack 기반 레거시 Vue 2 클라이언트 (포트 8000)

**인프라:**
- `docker/mysql/`: 데이터베이스 컨테이너화
- `configuration/`: 외부 설정 파일
- `document/`: 데이터베이스 스키마 및 설정 스크립트

## 주요 개발 명령어

### 프론트엔드 개발

**최신 Vue 클라이언트 (front-client/):**
```bash
cd front-client
npm install
npm run serve          # 개발 서버
npm run local          # 로컬 환경 (포트 3000)
npm run test           # 테스트 환경 (포트 3000)
npm run build          # 프로덕션 빌드
npm run lint           # ESLint
```

**레거시 Vue 클라이언트 (mas-farm-client/):**
```bash
cd mas-farm-client
npm install
npm run dev            # 개발 서버
npm run build          # 프로덕션 빌드
npm run test           # 테스트 실행 (unit + e2e)
npm run lint           # ESLint
```

### 백엔드 개발

**전체 모듈 빌드:**
```bash
mvn clean install -DskipTests
```

**특정 서비스 빌드:**
```bash
cd msa-farm-server
mvn clean package -DskipTests

cd msa-farm-server/msa-farm-server-api
mvn spring-boot:run    # API 서버 실행
```

**테스트 실행:**
```bash
mvn test               # 참고: 현재 설정에서 테스트는 스킵됨
```

## 기술 스택

### 백엔드
- **프레임워크:** Spring Boot 2.3.0 + Spring WebFlux (리액티브)
- **데이터베이스:** R2DBC with MySQL/H2 지원
- **Java 버전:** 11-15 (혼재 설정)
- **빌드 도구:** Maven
- **아키텍처:** Spring Cloud Config 기반 마이크로서비스
- **모니터링:** Spring Boot Admin

### 프론트엔드
- **프레임워크:** Vue.js 2.x
- **빌드 도구:**
  - Vue CLI 4.x (front-client)
  - Webpack 3.x (mas-farm-client 레거시)
- **UI 라이브러리:** Bootstrap Vue, TUI Grid
- **HTTP 클라이언트:** Axios
- **상태 관리:** Vuex
- **국제화:** Vue I18n

## 주요 개발 참고사항

### 데이터베이스 설정
- 데이터베이스 스키마는 `document/` 디렉터리에 있음
- 전통적인 JPA 대신 리액티브 R2DBC 사용
- 개발 시 MySQL과 H2 데이터베이스 모두 지원

### 서비스 포트
- API 서버: 8090
- Config 서버: 9000  
- 최신 프론트엔드: 3000 (local/test 모드)
- 레거시 프론트엔드: 8000
- 사용자 웹 클라이언트: 8000

### 코드 패턴
- 백엔드는 WebFlux를 사용한 리액티브 프로그래밍 패턴
- 프론트엔드는 Vue.js 컴포넌트 기반 아키텍처
- 마이크로서비스 간 보안 및 도메인 모델 공유
- Spring Cloud Config를 통한 중앙집중식 설정 관리

### 테스트
- 백엔드 테스트는 현재 비활성화됨 (`<skipTests>true</skipTests>`)
- 프론트엔드는 Jest 단위 테스트 및 Nightwatch e2e 테스트 포함 (레거시 클라이언트)
- 필요시 skipTests 설정을 제거하여 테스트 활성화

## 설정 관리
- `configuration/` 디렉터리의 외부화된 설정
- Spring Cloud Config 서버가 분산 설정 관리
- 로컬/테스트/프로덕션 배포를 위한 환경별 설정