import { defineStore } from 'pinia'

// 타입 정의
export interface Observation {
  id: number
  entityIdentificationNumber: string
  observationDate: string
  observationTime: string
  observer: string
  healthStatus: string
  temperature?: number
  weight?: number
  pulse?: number
  respiration?: number
  appetite: string
  activity: string
  excretion?: string
  rumination?: string
  coatCondition?: string
  eyeCondition?: string
  noseCondition?: string
  hoofCondition?: string
  observations: string
  notes?: string
  recommendedActions?: string
  nextObservationDate?: string
  createdAt: string
  updatedAt?: string
}

export interface ObservationSearchParams {
  entityIdentificationNumber?: string
  startDate?: string
  endDate?: string
  healthStatus?: string
  page?: number
  size?: number
}

export interface PaginationInfo {
  currentPage: number
  pageSize: number
  total: number
  totalPages: number
}

interface ObservationState {
  observationList: Observation[]
  currentObservation: Observation | null
  pagination: PaginationInfo
  loading: boolean
  searchParams: ObservationSearchParams
}

export const useObservationStore = defineStore('observation', {
  state: (): ObservationState => ({
    observationList: [],
    currentObservation: null,
    pagination: {
      currentPage: 1,
      pageSize: 12,
      total: 0,
      totalPages: 0
    },
    loading: false,
    searchParams: {}
  }),

  getters: {
    /**
     * 현재 페이지의 관찰 기록 목록
     */
    getCurrentPageObservation: (state) => state.observationList,

    /**
     * 총 관찰 기록 개수
     */
    getTotalObservation: (state) => state.pagination.total,

    /**
     * 로딩 상태
     */
    isLoading: (state) => state.loading,

    /**
     * 건강 상태별 관찰 기록 개수
     */
    getObservationByHealthStatus: (state) => {
      const healthStats = state.observationList.reduce((acc, obs) => {
        acc[obs.healthStatus] = (acc[obs.healthStatus] || 0) + 1
        return acc
      }, {} as Record<string, number>)
      
      return healthStats
    },

    /**
     * 최근 관찰 기록 (최대 10개)
     */
    getRecentObservation: (state) => {
      return state.observationList
        .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime())
        .slice(0, 10)
    },

    /**
     * 특정 소의 관찰 기록
     */
    getObservationByEntity: (state) => (entityId: string) => {
      return state.observationList.filter(
        obs => obs.entityIdentificationNumber === entityId
      )
    }
  },

  actions: {
    /**
     * 관찰 기록 목록 조회
     */
    async fetchObservationList(params: ObservationSearchParams = {}) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        // 검색 파라미터 업데이트
        this.searchParams = { ...this.searchParams, ...params }
        
        const response = await get<{
          content: Observation[]
          totalElements: number
          totalPages: number
          number: number
          size: number
        }>('/observation/list', this.searchParams)
        
        this.observationList = response.content
        this.pagination = {
          currentPage: response.number + 1,
          pageSize: response.size,
          total: response.totalElements,
          totalPages: response.totalPages
        }
        
      } catch (error) {
        console.error('관찰 기록 목록 조회 실패:', error)
        this.observationList = []
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 관찰 기록 상세 정보 조회
     */
    async fetchObservationDetail(id: number) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        const observation = await get<Observation>(`/observation/${id}`)
        this.currentObservation = observation
        
        return observation
      } catch (error) {
        console.error('관찰 기록 상세 정보 조회 실패:', error)
        this.currentObservation = null
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 관찰 기록 추가
     */
    async createObservation(observationData: Omit<Observation, 'id' | 'createdAt' | 'updatedAt'>) {
      this.loading = true
      
      try {
        const { post } = useApi()
        
        const newObservation = await post<Observation>('/observation', {
          ...observationData,
          createdAt: new Date().toISOString()
        })
        
        // 목록에 새 관찰 기록 추가 (첫 페이지에 있는 경우)
        if (this.pagination.currentPage === 1) {
          this.observationList.unshift(newObservation)
          if (this.observationList.length > this.pagination.pageSize) {
            this.observationList.pop()
          }
        }
        
        this.pagination.total += 1
        
        return newObservation
      } catch (error) {
        console.error('관찰 기록 추가 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 관찰 기록 수정
     */
    async updateObservation(id: number, observationData: Partial<Observation>) {
      this.loading = true
      
      try {
        const { put } = useApi()
        
        const updatedObservation = await put<Observation>(`/observation/${id}`, {
          ...observationData,
          updatedAt: new Date().toISOString()
        })
        
        // 목록에서 해당 관찰 기록 정보 업데이트
        const index = this.observationList.findIndex(obs => obs.id === id)
        if (index !== -1) {
          this.observationList[index] = updatedObservation
        }
        
        // 현재 관찰 기록 정보 업데이트
        if (this.currentObservation?.id === id) {
          this.currentObservation = updatedObservation
        }
        
        return updatedObservation
      } catch (error) {
        console.error('관찰 기록 수정 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 관찰 기록 삭제
     */
    async deleteObservation(id: number) {
      this.loading = true
      
      try {
        const { del } = useApi()
        
        await del(`/observation/${id}`)
        
        // 목록에서 해당 관찰 기록 제거
        this.observationList = this.observationList.filter(obs => obs.id !== id)
        this.pagination.total -= 1
        
        // 현재 관찰 기록이 삭제된 기록인 경우 초기화
        if (this.currentObservation?.id === id) {
          this.currentObservation = null
        }
        
      } catch (error) {
        console.error('관찰 기록 삭제 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 건강 상태 통계 조회
     */
    async fetchHealthStats() {
      try {
        const { get } = useApi()
        
        const stats = await get<{
          totalCount: number
          healthStatusStats: Record<string, number>
          recentCount: number
          criticalCount: number
        }>('/observation/stats')
        
        return stats
      } catch (error) {
        console.error('건강 상태 통계 조회 실패:', error)
        return {
          totalCount: 0,
          healthStatusStats: {},
          recentCount: 0,
          criticalCount: 0
        }
      }
    },

    /**
     * 특정 소의 관찰 히스토리 조회
     */
    async fetchObservationHistory(entityIdentificationNumber: string) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        const history = await get<Observation[]>(`/observation/history/${entityIdentificationNumber}`)
        
        return history
      } catch (error) {
        console.error('관찰 히스토리 조회 실패:', error)
        return []
      } finally {
        this.loading = false
      }
    },

    /**
     * 검색 조건 초기화
     */
    resetSearchParams() {
      this.searchParams = {}
    },

    /**
     * 현재 관찰 기록 정보 초기화
     */
    clearCurrentObservation() {
      this.currentObservation = null
    },

    /**
     * 상태 초기화
     */
    resetState() {
      this.observationList = []
      this.currentObservation = null
      this.pagination = {
        currentPage: 1,
        pageSize: 12,
        total: 0,
        totalPages: 0
      }
      this.searchParams = {}
    }
  }
})