package com.skan.farm.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequiredArgsConstructor
public class ObserveController {

    private final ObserveJooqRepository observeJooqRepository;

    @GetMapping(value = "/observation.diary/list", produces = "application/json; charset=utf8")
    private Object listView(@ModelAttribute ObservationDiary requestObservationDiary,
                            @RequestParam(required = false, defaultValue = "0") Integer page,
                            @RequestParam(required = false, defaultValue = "100") Integer size) {

        try {

            PageableRequest pageableRequest = new PageableRequest(page-1, size);
            PageImpl<ObservationDiary> salesStatusPage = (PageImpl<ObservationDiary>) observeJooqRepository.findByAll(requestObservationDiary, pageableRequest);

            int total = (int) salesStatusPage.getTotalElements();
            List<ObservationDiary> observations = salesStatusPage.getContents();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            String jsonStr = objectMapper.writeValueAsString(observations);

            String textBlock = """
                    {
                         "result": true,
                         "data": {
                           "contents": %s,
                           "pagination": {
                             "page": %d,
                             "totalCount": %d
                           }
                         }
                       }
                    """;

            String result = String.format(textBlock, jsonStr, page, total);
            log.debug("response data = {} ", result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>().ERROR(e.getMessage());

        }

    }
}
