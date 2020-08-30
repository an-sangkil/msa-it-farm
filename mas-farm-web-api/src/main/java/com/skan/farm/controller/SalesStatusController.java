package com.skan.farm.controller;

import com.skan.farm.domain.model.SalesStatus;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.PageableRequest;
import com.skan.farm.repository.jooq.SalesStatusJooqRepository;
import com.skan.farm.response.AbstractResponse;
import com.skan.farm.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Description : 판매 현황 
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@RestController
@RequiredArgsConstructor
public class SalesStatusController {

    final SalesStatusJooqRepository salesStatusJooqRepository;

    @GetMapping("/sales.status/list")
    private AbstractResponse listView(@ModelAttribute SalesStatus salesStatus,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "100") Integer size) {

        try {

            PageableRequest pageableRequest = new PageableRequest(page,size);
            PageImpl<SalesStatus> salesStatusPage = salesStatusJooqRepository.findByAll(salesStatus,pageableRequest);
            return new Response<>().OK(salesStatusPage);

        }catch (Exception e) {

            return new Response<>().ERROR(e.getMessage());

        }

    }
}
