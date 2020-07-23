package com.skan.farm.paging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-23
 */
@Slf4j
class PageNationJpaUtilsTest {

    PageNationJpaUtils  pageNationJpaUtils = new  PageNationJpaUtils();

    @Test
    public void pageBlockTest() {
        int currentNo = 21;
        int pagingSize = 10;
        int totalPageSize = 31;

        int begin  = pageNationJpaUtils.pagingBegin(currentNo);
        int end = pageNationJpaUtils.pagingEnd(totalPageSize);

        log.debug("begin {} , end {}", begin,end);

        //System.out.println(Math.floor(((currentNo - 1) / pagingSize)));
    }



}