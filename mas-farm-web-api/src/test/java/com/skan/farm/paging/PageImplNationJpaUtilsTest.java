package com.skan.farm.paging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
class PageImplNationJpaUtilsTest {

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

    @Test
    public void splitTest() {
        String test = "#@ #@ #@";
        String[] testAr = test.split("#@");
        System.out.println("1 = " + testAr[0]);
        System.out.println("2 = " + testAr[1]);
        System.out.println("3 = " + testAr[2]);


        String dateStr = "2021-05-07 18:13:32";
        LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}