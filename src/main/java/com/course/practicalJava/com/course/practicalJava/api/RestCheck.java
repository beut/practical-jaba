package com.course.practicalJava.com.course.practicalJava.api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api")

public class RestCheck {

    @RequestMapping(value = "/welcome")
    public String welcome() {
        return StringUtils.join("Welcome", " Idea", "JS");
    }

    @RequestMapping("/time")
    public String time() {
        return LocalTime.now().toString();
    }
}

