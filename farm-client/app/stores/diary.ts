import { defineStore } from 'pinia'

// 타입 정의
export interface Diary {
  id: number
  title: string
  summary: string
  content: string
  category: string
  workDate: string
  weather?: string
  temperature?: number
  humidity?: number
  startTime?: string
  endTime?: string
  notes?: string
  author: string
  createdAt: string
  updatedAt?: string
}

export interface DiarySearchParams {
  startDate?: string
  endDate?: string
  category?: string
  keyword?: string
  page?: number
  size?: number
}

export interface PaginationInfo {
  currentPage: number
  pageSize: number
  total: number
  totalPages: number
}

interface DiaryState {
  diaryList: Diary[]
  currentDiary: Diary | null
  pagination: PaginationInfo
  loading: boolean
  searchParams: DiarySearchParams
}

export const useDiaryStore = defineStore('diary', {
  state: (): DiaryState => ({
    diaryList: [],
    currentDiary: null,
    pagination: {
      currentPage: 1,
      pageSize: 10,
      total: 0,
      totalPages: 0
    },
    loading: false,
    searchParams: {}
  }),

  getters: {
    /**
     * 현재 페이지의 일지 목록
     */
    getCurrentPageDiary: (state) => state.diaryList,

    /**
     * 총 일지 개수
     */
    getTotalDiary: (state) => state.pagination.total,

    /**
     * 로딩 상태
     */
    isLoading: (state) => state.loading,

    /**
     * 카테고리별 일지 개수
     */
    getDiaryByCategory: (state) => {
      const categories = state.diaryList.reduce((acc, diary) => {
        acc[diary.category] = (acc[diary.category] || 0) + 1
        return acc
      }, {} as Record<string, number>)
      
      return categories
    },

    /**
     * 최근 일지 (최대 5개)
     */
    getRecentDiary: (state) => {
      return state.diaryList
        .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime())
        .slice(0, 5)
    }
  },

  actions: {
    /**
     * 일지 목록 조회
     */
    async fetchDiaryList(params: DiarySearchParams = {}) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        // 검색 파라미터 업데이트
        this.searchParams = { ...this.searchParams, ...params }
        
        const response = await get<{
          content: Diary[]
          totalElements: number
          totalPages: number
          number: number
          size: number
        }>('/diary/list', this.searchParams)
        
        this.diaryList = response.content
        this.pagination = {
          currentPage: response.number + 1,
          pageSize: response.size,
          total: response.totalElements,
          totalPages: response.totalPages
        }
        
      } catch (error) {
        console.error('일지 목록 조회 실패:', error)
        this.diaryList = []
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 일지 상세 정보 조회
     */
    async fetchDiaryDetail(id: number) {
      this.loading = true
      
      try {
        const { get } = useApi()
        
        const diary = await get<Diary>(`/diary/${id}`)
        this.currentDiary = diary
        
        return diary
      } catch (error) {
        console.error('일지 상세 정보 조회 실패:', error)
        this.currentDiary = null
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 일지 작성
     */
    async createDiary(diaryData: Omit<Diary, 'id' | 'createdAt' | 'updatedAt'>) {
      this.loading = true
      
      try {
        const { post } = useApi()
        
        const newDiary = await post<Diary>('/diary', {
          ...diaryData,
          createdAt: new Date().toISOString()
        })
        
        // 목록에 새 일지 추가 (첫 페이지에 있는 경우)
        if (this.pagination.currentPage === 1) {
          this.diaryList.unshift(newDiary)
          if (this.diaryList.length > this.pagination.pageSize) {
            this.diaryList.pop()
          }
        }
        
        this.pagination.total += 1
        
        return newDiary
      } catch (error) {
        console.error('일지 작성 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 일지 수정
     */
    async updateDiary(id: number, diaryData: Partial<Diary>) {
      this.loading = true
      
      try {
        const { put } = useApi()
        
        const updatedDiary = await put<Diary>(`/diary/${id}`, {
          ...diaryData,
          updatedAt: new Date().toISOString()
        })
        
        // 목록에서 해당 일지 정보 업데이트
        const index = this.diaryList.findIndex(diary => diary.id === id)
        if (index !== -1) {
          this.diaryList[index] = updatedDiary
        }
        
        // 현재 일지 정보 업데이트
        if (this.currentDiary?.id === id) {
          this.currentDiary = updatedDiary
        }
        
        return updatedDiary
      } catch (error) {
        console.error('일지 수정 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 일지 삭제
     */
    async deleteDiary(id: number) {
      this.loading = true
      
      try {
        const { del } = useApi()
        
        await del(`/diary/${id}`)
        
        // 목록에서 해당 일지 제거
        this.diaryList = this.diaryList.filter(diary => diary.id !== id)
        this.pagination.total -= 1
        
        // 현재 일지가 삭제된 일지인 경우 초기화
        if (this.currentDiary?.id === id) {
          this.currentDiary = null
        }
        
      } catch (error) {
        console.error('일지 삭제 실패:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * 카테고리별 일지 개수 조회
     */
    async fetchDiaryStats() {
      try {
        const { get } = useApi()
        
        const stats = await get<{
          totalCount: number
          categoryStats: Record<string, number>
          recentCount: number
        }>('/diary/stats')
        
        return stats
      } catch (error) {
        console.error('일지 통계 조회 실패:', error)
        return {
          totalCount: 0,
          categoryStats: {},
          recentCount: 0
        }
      }
    },

    /**
     * 검색 조건 초기화
     */
    resetSearchParams() {
      this.searchParams = {}
    },

    /**
     * 현재 일지 정보 초기화
     */
    clearCurrentDiary() {
      this.currentDiary = null
    },

    /**
     * 상태 초기화
     */
    resetState() {
      this.diaryList = []
      this.currentDiary = null
      this.pagination = {
        currentPage: 1,
        pageSize: 10,
        total: 0,
        totalPages: 0
      }
      this.searchParams = {}
    }
  }
})