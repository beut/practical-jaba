package com.course.practicalJava.api.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Default Rest Api", description = "Documentation for default rest api")
public class DefaultRestApi {

    private Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

    @RequestMapping(value = "/welcome")
    @Operation(summary = "Welcome", description = "Default rest api, welcome method")
    public String welcome() {
//        System.out.println(StringUtils.join("Welcome", " Idea", "JS"));
        LOG.info(StringUtils.join("Welcome", " Idea", "JS"));
        return "Welcome to Rest api";
    }

    @RequestMapping("/time")
    public String time() {
        return LocalTime.now().toString();
    }

    @GetMapping(value = "/header-one")
    public String headerByAnnotation(@RequestHeader(name = "User-agent") String headerUserAgent, @RequestHeader(name="Practical-java", required = false) String headerPracticalJava ) {
        return "User-agent : " + headerUserAgent + " Practical-java : " + headerPracticalJava;
    }

    @GetMapping(value = "/header-two")
    //package reactive!!
    public String headerByRequest(ServerHttpRequest request) {

        return "User-agent : " + request.getHeaders().getValuesAsList("User-agent") + ", Practical-java : "
                + request.getHeaders().getValuesAsList("Practical-java");

    }


}

