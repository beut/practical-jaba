package com.course.practicalJava.com.course.practicalJava.api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")

public class RestCheck {

    private Logger LOG = LoggerFactory.getLogger(RestCheck.class);

    @RequestMapping(value = "/welcome")
    public String welcome() {
//        System.out.println(StringUtils.join("Welcome", " Idea", "JS"));
        LOG.info(StringUtils.join("Welcome", " Idea", "JS"));
        return "Welcome to Rest api";
    }

    @RequestMapping("/time")
    public String time() {
        return LocalTime.now().toString();
    }
}

