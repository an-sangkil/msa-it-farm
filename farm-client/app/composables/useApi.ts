/**
 * API 통신을 위한 Composable
 * Nuxt 4의 $fetch를 활용하여 타입 안전한 API 통신 제공
 */

interface ApiResponse<T = any> {
  data: T
  success: boolean
  message?: string
}

interface ApiError {
  statusCode: number
  statusMessage: string
  data?: any
}

interface PaginationParams {
  page?: number
  size?: number
}

interface SearchParams {
  [key: string]: any
}

export const useApi = () => {
  const config = useRuntimeConfig()
  
  // 기본 API URL (환경 변수에서 가져오기)
  const baseURL = config.public.apiBase || 'http://localhost:8090'
  
  /**
   * GET 요청
   */
  const get = async <T = any>(
    url: string, 
    params?: SearchParams & PaginationParams
  ): Promise<T> => {
    try {
      const response = await $fetch<ApiResponse<T>>(`${baseURL}${url}`, {
        method: 'GET',
        query: params
      })
      
      if (!response.success) {
        throw createError({
          statusCode: 400,
          statusMessage: response.message || 'API 요청 실패'
        })
      }
      
      return response.data
    } catch (error) {
      console.error('GET 요청 실패:', error)
      throw error
    }
  }
  
  /**
   * POST 요청
   */
  const post = async <T = any>(
    url: string, 
    body: any
  ): Promise<T> => {
    try {
      const response = await $fetch<ApiResponse<T>>(`${baseURL}${url}`, {
        method: 'POST',
        body
      })
      
      if (!response.success) {
        throw createError({
          statusCode: 400,
          statusMessage: response.message || 'API 요청 실패'
        })
      }
      
      return response.data
    } catch (error) {
      console.error('POST 요청 실패:', error)
      throw error
    }
  }
  
  /**
   * PUT 요청
   */
  const put = async <T = any>(
    url: string, 
    body: any
  ): Promise<T> => {
    try {
      const response = await $fetch<ApiResponse<T>>(`${baseURL}${url}`, {
        method: 'PUT',
        body
      })
      
      if (!response.success) {
        throw createError({
          statusCode: 400,
          statusMessage: response.message || 'API 요청 실패'
        })
      }
      
      return response.data
    } catch (error) {
      console.error('PUT 요청 실패:', error)
      throw error
    }
  }
  
  /**
   * DELETE 요청
   */
  const del = async <T = any>(url: string): Promise<T> => {
    try {
      const response = await $fetch<ApiResponse<T>>(`${baseURL}${url}`, {
        method: 'DELETE'
      })
      
      if (!response.success) {
        throw createError({
          statusCode: 400,
          statusMessage: response.message || 'API 요청 실패'
        })
      }
      
      return response.data
    } catch (error) {
      console.error('DELETE 요청 실패:', error)
      throw error
    }
  }
  
  return {
    get,
    post,
    put,
    del,
    baseURL
  }
}