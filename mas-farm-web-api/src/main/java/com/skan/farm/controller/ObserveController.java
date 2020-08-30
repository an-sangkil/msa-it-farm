package com.skan.farm.controller;

import com.skan.farm.domain.model.ObservationDiary;
import com.skan.farm.domain.model.SalesStatus;
import com.skan.farm.domain.tables.JCattleSellStoreInformation;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import com.skan.farm.paging.PageableRequest;
import com.skan.farm.repository.jooq.ObserveJooqRepository;
import com.skan.farm.response.AbstractResponse;
import com.skan.farm.response.Response;
import lombok.RequiredArgsConstructor;
import org.jooq.Require;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 * Description : 관찰 일지
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@RestController
@RequiredArgsConstructor
public class ObserveController {

    private final ObserveJooqRepository observeJooqRepository;

    @GetMapping("/observation.diary/list")
    private AbstractResponse listView(@ModelAttribute ObservationDiary  requestObservationDiary,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "100") Integer size) {

        try {

            PageableRequest pageableRequest = new PageableRequest(page,size);
            PageImpl<List<ObservationDiary>> salesStatusPage = (PageImpl<List<ObservationDiary>>) observeJooqRepository.findByAll(requestObservationDiary,pageableRequest);
            return new Response<>().OK(salesStatusPage);

        }catch (Exception e) {

            return new Response<>().ERROR(e.getMessage());

        }

    }
}
