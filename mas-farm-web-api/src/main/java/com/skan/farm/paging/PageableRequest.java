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
@JsonIgnoreProperties(ignoreUnknown = false)
public class PageableRequest implements Pageable {

    /**
     * 현제 페이지
     */
    private int page;

    /**
     * 화면에 출력할 데이터 사이즈
     */
    private int size;

    @Deprecated
    private Sort sort;
    @Deprecated
    private Sort.Direction direction;

    private PageableRequest() {
    }

    public PageableRequest(int page, int size) {
        //this.page = page <= 0 ? 1 : page;
        this.page = page;
        this.setSize(size);
    }

    private void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 100;

        if (size > 101) {
            throw new IllegalArgumentException("100 보다 작거나 같아야 합니다.");
        }

        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;

    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    /**
     * 다음 페이지
     *
     * @return
     */
    @Override
    public PageableRequest next() {
        return new PageableRequest(getPageNumber() + 1, getPageSize());
    }

    /**
     * 이전 페이지
     *
     * @return
     */
    @Override
    public PageableRequest previous() {
        return getPageNumber() == 0 ? this.first() : new PageableRequest(getPageNumber() - 1, getPageSize());
    }

    @Override
    public PageableRequest first() {
        return new PageableRequest(0, getPageSize());
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
     * 화면에 출력할 데이터 사이즈
     *
     * @return
     */
    @Override
    public int getPageSize() {
        return size;
    }

    /**
     * 현재 페이지 번호
     *
     * @return
     */
    @Override
    public int getPageNumber() {
        return page;
    }
}
