/**
 * <pre>
 * Class Name  : PageUtils.java
 * Description : JPA 전용 페이징 유틸 클레스
 *                  - 외국 페이징 방식 커스터 마이징
 *
 *
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 1. 5.          ask               최초생성
 * </pre>
 *
 * @author ahn
 * @version Copyright (C) 2016 by SKAN.COMPANY All right reserved.
 * @since 2016. 1. 5.
 */
package com.skan.farm.paging;

/**
 *
 * 페이징 네비에 사용되는 클레스
 * <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,<10>
 *
 * 페이징 유틸,
 * JPA 사용시 북미식 페이징 방법으로 한국정서와 맞지 않아 해당 클레스 사용 하여 begin, end 페이지 생성
 *
 * total page : 123 , block_paging_size = 10
 *
 * ex ) current 11 , begin = 11, end = 20
 * ex ) current 12 , begin = 11, end = 20
 * ex ) current 21 , begin = 21, end = 30
 * ex ) current 23 , begin = 21, end = 30
 * ex ) current 33 , begin = 31, end = 40
 *
 */
public class PageNationJpaUtils {

    // 화면에 표시 되어야할 페이징수 (페이지네이션 블록 값)
    private int BLOCK_PAGING_SIZE;
    private int begin;
    private int end;

    public PageNationJpaUtils() {
        this.BLOCK_PAGING_SIZE = 10;
    }


    public PageNationJpaUtils(int block_paging_size) {
        this.BLOCK_PAGING_SIZE = block_paging_size;
    }

    /*
     * 현재 선택퇸 페이지의,   페이징 시작 값
     *
     * <example>
     * input current : 3     ->  <1>  <2>  <3>  <4>  <5>  ...  return <1>
     * input current : 12,13 ->  <11> <12> <13> <14> <15> ...  return <11>
     *
     *
     * @param currentNo
     * @return
     */
    public int pagingBegin(int currentNo) {
        // 선택된 페이지 값에서  Block 사이즈로 나눈 값에 소수점 이하를 버림한다.
        this.begin = Math.max(1, (int) Math.floor(((currentNo - 1) / this.BLOCK_PAGING_SIZE)) * this.BLOCK_PAGING_SIZE + 1);
        return this.begin;
    }

    /*
     * 현재 선택퇸 페이지의 종료 페이지 값
     * @param totalPages 전체  페이징 갯수, PageImpl 에서 계산한것 사용.
     * @return
     */
    public int pagingEnd(int totalPages) {
        // 전체 페이지수 가  (시작 페이지 + BLOCK_PAGE_SIZE) 보다큰경우  마지막 페이지수에 -1
        this.end = Math.min(this.begin + this.BLOCK_PAGING_SIZE, totalPages);
        if (totalPages >= (begin + BLOCK_PAGING_SIZE)) {
            end = end - 1;
        }

        return this.end;
    }


    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

}
