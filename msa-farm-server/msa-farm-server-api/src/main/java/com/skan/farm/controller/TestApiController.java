package com.skan.farm.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;

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

    @GetMapping("/get/test")
    public TestModel getTest(ServerHttpRequest serverHttpRequest) {
        TestModel testModel = new TestModel();
        testModel.setId("id1");
        testModel.setName("name2");
        return testModel;
    }

}

@Getter@Setter
@ToString
class TestModel {
    String id;
    String name;
}
