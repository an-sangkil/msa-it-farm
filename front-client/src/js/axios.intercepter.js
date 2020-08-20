import axios from "axios"

/*
 * Axios Interceptor
 *  - Requester
 *  - Response
 */


axios.interceptors.request.use((config)=>{
  config.headers.token = '';
  config.headers.refresh_token = ''

  //console.log('config = ', config)

  return config
}, (error)=> {

  return Promise.reject(error)
})


axios.interceptors.response.use((response)=>{

  return response;
} ,(error)=> {

  return
})


export default axios;
