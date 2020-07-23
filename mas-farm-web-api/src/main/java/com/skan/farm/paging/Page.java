package com.skan.farm.paging;

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
@ToString
public class Page <T> extends PageableJooq {

    /**
     * 전체 페이지  사이즈
     */
    private int total;
    private PageableDefault pageableDefault;

    /**
     * 데이터
     */
    @Getter
    List<T> contents;

    public Page(int page, int size) {
        super(page, size);
    }

    /**
     * 전체 페이지 값
     * ()
     */
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    protected int getSize() {
        return contents != null && contents.size() > 0 ? contents.size() : 0;
    }

    public boolean hasNext() {
        return pageableDefault.getPageNumber() + 1 < getTotalPages();
    }

    public long getTotalElements() {
        return total;
    }

    public boolean isLast() {
        return !hasNext();
    }

    public PageableDefault getPageable() {
        return pageableDefault;
    }
}
