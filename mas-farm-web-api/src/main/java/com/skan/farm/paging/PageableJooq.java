package com.skan.farm.paging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Sort;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableJooq implements PageableDefault {

    /**
     * 페이지
     */
    private int page;

    /**
     * 출력 데이터
     */
    private int size;


    @Deprecated
    private Sort sort;
    @Deprecated
    private Sort.Direction direction;

    private PageableJooq() {}

    public PageableJooq(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 100;

        if (size > 101) {
            throw new IllegalArgumentException("100 보다 작거나 같아야 합니다.");
        }

        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;

    }



    /**
     * 다음 페이지
     *
     * @return
     */
    @Override
    public PageableJooq next() {
        return new PageableJooq(getPageNumber() + 1, getPageSize());
    }

    /**
     * 이전 페이지
     *
     * @return
     */
    @Override
    public PageableJooq previous() {
        return getPageNumber() == 0 ? this : new PageableJooq(getPageNumber() - 1, getPageSize());
    }
    @Override
    public PageableJooq first() {
        return new PageableJooq(0, getPageSize());
    }

    /**
     * 사용자 이벤트 클릭에 따른 위치 주소값 변경값.
     * (페이지 * 화면에 표시되는 출력 데이터)
     *
     * @return
     */
    @Override
    public long getOffset() {
        return (long) page * (long) size;
    }

    /**
     * 출력 데이터 사이즈
     * @return
     */
    @Override
    public int getPageSize() {
        return size;
    }

    /**
     * 페이지 번호
     * @return
     */
    @Override
    public int getPageNumber() {
        return page;
    }



}
