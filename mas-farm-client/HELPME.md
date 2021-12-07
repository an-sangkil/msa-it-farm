# 사용하지 않는 프로젝트


### 사전 준비
 - node js npm
 - vue.js 설치 vue-cli version
```shell script
# npm 으로 vue-cli 설치
npm install -g @vue/cli

#global addon <vue init>
npm install -g @vue/cli-init

# project Create vue.2.X
vue init webpack frontend
# project Create vue.3.X after
vue create frontend
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
> npm install --save vuex
> npm install --save axios
> npm install --save vuex

# --save 종속성 추가
# --save-dev 개발시만 사용하고 업로드시 사용하지 않음
# -g 운도우 운영체제 전역에 설치
```
