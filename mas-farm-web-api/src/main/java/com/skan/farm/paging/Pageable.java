package com.skan.farm.paging;

public interface Pageable {

    PageableRequest next();

    PageableRequest previous();

    PageableRequest first();

    long getOffset();

    int getPageSize();

    int getPageNumber();
}
