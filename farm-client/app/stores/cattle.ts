import { defineStore } from 'pinia'

// 타입 정의
export interface Cattle {
  entityIdentificationNumber: string
  entityManagementNumber: string
  roomNumber?: string
  calvesCount?: number
  inseminationCount?: number
  expectedDateChildbirth?: string
  gender: 'MALE' | 'FEMALE'
  birthDay?: string
  numberOfMonth?: number
  parentMomNo?: string
  notes?: string
}

export interface CattleSearchParams {
  entityIdentificationNumber?: string
  gender?: string
  roomNumber?: string
  numberOfMonth?: string
  birthDate?: string
  page?: number
  size?: number
}

export interface PaginationInfo {
  currentPage: number
  pageSize: number
  total: number
  totalPages: number
}

interface CattleState {
  cattleList: Cattle[]
  currentCattle: Cattle | null
  pagination: PaginationInfo
  loading: boolean
  searchParams: CattleSearchParams
}

export const useCattleStore = defineStore('cattle', {
  state: (): CattleState => ({
    cattleList: [],
    currentCattle: null,
    pagination: {
      currentPage: 1,
      pageSize: 20,
      total: 0,
      totalPages: 0
    },
    loading: false,
    searchParams: {}
  }),

  getters: {
    /**
     * 현재 페이지의 소 목록
     */
    getCurrentPageCattle: (state) => state.cattleList,

    /**
     * 총 소 개수
     */
    getTotalCattle: (state) => state.pagination.total,

    /**
     * 로딩 상태
     */
    isLoading: (state) => state.loading,

    /**
     * 성별별 소 개수
     */
    getCattleByGender: (state) => {
      const male = state.cattleList.filter(cattle => cattle.gender === 'MALE').length
      const female = state.cattleList.filter(cattle => cattle.gender === 'FEMALE').length
      return { male, female }
    }
  },

  actions: {
    /**
     * 소 목록 조회
     */
    async fetchCattleList(params: CattleSearchParams = {}) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        // 검색 파라미터 업데이트
        this.searchParams = { ...this.searchParams, ...params }
        
        const response = await get<{
          content: Cattle[]
          totalElements: number
          totalPages: number
          number: number
          size: number
        }>('/cattle/cattle_management_list', this.searchParams)
        
        this.cattleList = response.content
        this.pagination = {
          currentPage: response.number + 1, // API는 0부터 시작, UI는 1부터 시작
          pageSize: response.size,
          total: response.totalElements,
          totalPages: response.totalPages
        }
        
      } catch (error) {
        console.error('소 목록 조회 실패:', error)
        this.cattleList = []
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 소 상세 정보 조회
     */
    async fetchCattleDetail(entityManagementNumber: string, entityIdentificationNumber: string) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        const cattle = await get<Cattle>(`/cattle/${entityManagementNumber}/${entityIdentificationNumber}`)
        this.currentCattle = cattle
        
        return cattle
      } catch (error) {
        console.error('소 상세 정보 조회 실패:', error)
        this.currentCattle = null
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 소 등록
     */
    async createCattle(cattleData: Omit<Cattle, 'entityIdentificationNumber' | 'entityManagementNumber'> & {
      entityIdentificationNumber: string
      entityManagementNumber: string
    }) {
      this.loading = true
      
      try {
        const { post } = useApi()
        
        const newCattle = await post<Cattle>('/cattle', cattleData)
        
        // 목록에 새 소 추가 (첫 페이지에 있는 경우)
        if (this.pagination.currentPage === 1) {
          this.cattleList.unshift(newCattle)
          if (this.cattleList.length > this.pagination.pageSize) {
            this.cattleList.pop()
          }
        }
        
        this.pagination.total += 1
        
        return newCattle
      } catch (error) {
        console.error('소 등록 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 소 정보 수정
     */
    async updateCattle(entityManagementNumber: string, entityIdentificationNumber: string, cattleData: Partial<Cattle>) {
      this.loading = true
      
      try {
        const { put } = useApi()
        
        const updatedCattle = await put<Cattle>(`/cattle/${entityManagementNumber}/${entityIdentificationNumber}`, cattleData)
        
        // 목록에서 해당 소 정보 업데이트
        const index = this.cattleList.findIndex(
          cattle => cattle.entityManagementNumber === entityManagementNumber && 
                   cattle.entityIdentificationNumber === entityIdentificationNumber
        )
        
        if (index !== -1) {
          this.cattleList[index] = updatedCattle
        }
        
        // 현재 소 정보 업데이트
        if (this.currentCattle?.entityManagementNumber === entityManagementNumber && 
            this.currentCattle?.entityIdentificationNumber === entityIdentificationNumber) {
          this.currentCattle = updatedCattle
        }
        
        return updatedCattle
      } catch (error) {
        console.error('소 정보 수정 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 소 삭제
     */
    async deleteCattle(entityManagementNumber: string, entityIdentificationNumber: string) {
      this.loading = true
      
      try {
        const { del } = useApi()
        
        await del(`/cattle/${entityManagementNumber}/${entityIdentificationNumber}`)
        
        // 목록에서 해당 소 제거
        this.cattleList = this.cattleList.filter(
          cattle => !(cattle.entityManagementNumber === entityManagementNumber && 
                     cattle.entityIdentificationNumber === entityIdentificationNumber)
        )
        
        this.pagination.total -= 1
        
        // 현재 소가 삭제된 소인 경우 초기화
        if (this.currentCattle?.entityManagementNumber === entityManagementNumber && 
            this.currentCattle?.entityIdentificationNumber === entityIdentificationNumber) {
          this.currentCattle = null
        }
        
      } catch (error) {
        console.error('소 삭제 실패:', error)
        throw error
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
     * 현재 소 정보 초기화
     */
    clearCurrentCattle() {
      this.currentCattle = null
    },

    /**
     * 상태 초기화
     */
    resetState() {
      this.cattleList = []
      this.currentCattle = null
      this.pagination = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        totalPages: 0
      }
      this.searchParams = {}
    }
  }
})