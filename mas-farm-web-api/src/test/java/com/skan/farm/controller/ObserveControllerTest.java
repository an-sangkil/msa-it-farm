package com.skan.farm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.skan.farm.domain.model.ObservationDiary;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

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
 * @since 2020-10-13
 */
class ObserveControllerTest {


    @Test
    void textBlockTest() throws JsonProcessingException {

        var messageText = """
                    asdasd {0}
                """;
        Object[] argument = {"1"};
        var result = MessageFormat.format(messageText,argument);

        System.out.println(result);

        List<ObservationDiary> observations = new ArrayList<>();
        IntStream.rangeClosed(1,10).forEach(value -> {
            ObservationDiary observationDiary = new ObservationDiary();
            observationDiary.setEntityIdentificationNumber(String.valueOf(value));
            observations. add(observationDiary);
        });

        System.out.println("size = " +  observations.size());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        String jsonStr = objectMapper.writeValueAsString(observations);

        System.out.println("jsonStr = "+ jsonStr);

        String textBlock = """
                {
                     "result": true,
                     "data": {
                       "contents": %s,
                       "pagination": {
                         "page": %s,
                         "totalCount": 100
                       }
                     }
                   }
                """;
        System.out.printf((textBlock) + "%n",jsonStr, "1");


    }

}