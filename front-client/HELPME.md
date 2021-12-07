### 사전 준비 (vue-cli 로 프로젝트 구성)
 - node.js 설치 
 - vue.js 및 vue-cli 설치 
```shell script
# npm 으로 vue-cli 설치
npm install -g @vue/cli

#global addon <vue init>
npm install -g @vue/cli-init

# project Create
vue create {my-project-name} --no-git
```

### npm server 구동 스크립트
```shell script
# 필요 라이브러리 다운로드
> npn install

# 서버 구동
> npm run serve

# 빌드
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


### plug-in 설치 
 - 필요에 따라 할것 (안해도됨 이미 package.json 에선언되어 있음)
 - 인스톨시 package.json 에 자동 추가됨

```shell script
# Vue bootstrap 
npm install vue bootstrap-vue bootstrap
# Date 라이브러리
npm install moment --save
# 달력(날짜) 라이브러리 
npm install vue2-datepicker --save
# 다국어 라이브러리 
vue add i18n  
# JWT 암호화 라이브러리
npm install jsrsasign
```