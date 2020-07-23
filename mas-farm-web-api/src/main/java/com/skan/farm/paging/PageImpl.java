package com.skan.farm.paging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
@ToString
public class PageImpl<T> implements Page<T> {

    /**
     * 전체 Element 수
     */
    private int total;
    private int page;
    @Getter
    private Pageable pageable;

    /**
     * 데이터
     */
    @Getter
    List<T> contents;

    private PageImpl() {}

    public PageImpl(Pageable pageable, List<T> contents, int total) {
        this.pageable= pageable;
        this.contents = contents;
        this.total = total;
    }


    /**
     * 전체 페이지 값
     */
    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    @Override
    public long getTotalElements() {
        return total;
    }

    protected int getSize() {
        return pageable.getPageSize();
    }

    @Override
    public boolean hasNext() {
        return pageable.getPageNumber() + 1 < getTotalPages();
    }

    @Override
    public boolean isLast() {
        return !hasNext();
    }

}
