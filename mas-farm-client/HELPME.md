
### 사전 준비
 - node js npm
 - vue.js 설치 vue-cli version
```shell script
# npm 으로 vue-cli 설치
npm install -g @vue/cli

#global addon <vue init>
npm install -g @vue/cli-init

# project Create
vue init webpack frontend
```

### npm server 구동 스크립트
```shell script
# 필요 라이브러리 다운로드
> npn install

# 서버 구동
> npm start

# webpack 빌드  (spring application 의 @/resources 폴더로 빌드)
> npm run build
```

### vue.js 라이브러리 추가
- 라이브러리는 plugs.js 에 포함 되어 있음으로 추가할 필요 없음.
```shell script
> npm install vuex
> npm install axios
```