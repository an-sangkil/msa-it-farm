package com.skan.farm.controller;

import com.skan.farm.repository.TestR2dbcRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-15
 */
@CrossOrigin("*")
@RestController
public class TestApiController {

    TestR2dbcRepository testR2dbcRepository;

    public TestApiController(TestR2dbcRepository testR2dbcRepository) {
        this.testR2dbcRepository = testR2dbcRepository;
    }

    @GetMapping("/get/test")
    public TestModel getTest(ServerHttpRequest serverHttpRequest) {
        TestModel testModel = new TestModel();
        testModel.setId("id1");
        testModel.setName("name2");
        return testModel;
    }

    @GetMapping("/test/testR2dbcInsert")
    public void testR2dbcInsert(ServerHttpRequest serverHttpRequest) {

        var id = serverHttpRequest.getQueryParams().getFirst("id");
        var name = serverHttpRequest.getQueryParams().getFirst("name");

        testR2dbcRepository.insert(name, id);
    }

}

@Getter
@Setter
@ToString
class TestModel {
    String id;
    String name;
}
