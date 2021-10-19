# farm management
 -  vue.js

##### 프로젝트 구조 
    - msa-farm-client : web client (사용자 접근 WEB Client) (8000)
    
    - msa-farm-cloud : 클라우드 config-server (9000)
    
    - mas-farm-common
      |- msa-farm-common-security : 보안 모듈
      |- mas-farm-common-model : 공통 모델
       
    - mas-farm-server
      |- msa-farm-server-api : api web server (8090)
      |- msa-farm-server-model : api 전용 model
      |- msa-farm-server-repository : api 전용 레파지토리
    - mas-farm-monitoring : admin 모티터링 
    
    - docker : 필요한 docker application
    - document : 참고 자료 
