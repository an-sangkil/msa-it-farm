package com.skan.farm.paging;

import lombok.Getter;

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
public class Page <T> {

    /**
     * 전체 페이지  사이즈
     */
    private int total;
    private PageableDefault pageableDefault;

    private Page() {}

    public Page(PageableDefault pageableDefault, List<T> contents) {
        this.pageableDefault = pageableDefault;
        this.contents = contents;
        this.total = contents.size();
    }

    /**
     * 데이터
     */
    @Getter
    List<T> contents;

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
}
