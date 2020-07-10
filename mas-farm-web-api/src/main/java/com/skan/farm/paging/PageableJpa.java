package com.skan.farm.paging;

import org.springframework.data.domain.PageRequest;
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
public class PageableJpa {

    /**
     * 페이지
     */
    private int page;
    /**
     * 출력 데이터
     */
    private int size;
    private Sort sort;
    private Sort.Direction direction;
    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size, sort);
    }

}
