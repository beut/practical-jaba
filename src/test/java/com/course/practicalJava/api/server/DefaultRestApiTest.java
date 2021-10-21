package com.course.practicalJava.api.server;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalTime;

//import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultRestApiTest {

    @Autowired
    private WebTestClient webTestClient;



    @Test
    void welcome() {

        webTestClient.get().uri("/api/welcome").exchange().expectStatus().is2xxSuccessful().expectBody(String.class)
                .value(IsEqualIgnoringCase.equalToIgnoringCase("welcome to Rest api"));

    }

    @Test
    void time() {
        webTestClient.get().uri("/api/time").exchange().expectBody(String.class).value(v -> isCorrectTime(v));
    }

    private Object isCorrectTime(String time) {

        LocalTime response = LocalTime.parse(time);
        LocalTime now = LocalTime.now();
        LocalTime nowMinus30Sec = now.minusSeconds(30);

        assert(response.isAfter(nowMinus30Sec));
        assert(response.isBefore(now));
        return response;

    }

    @Test
    void headerByAnnotation() {

        String headerOne = "Spring Boot Test";
        String headerTwo = "Spring Boot Test on Practical Java";
        webTestClient.get().uri("/api/header-one").header("user-agent", headerOne )
                .header("Practical-java", headerTwo).exchange().expectBody(String.class)
                .value(v-> {
                    assert(v.contains(headerOne));
                    assert(v.contains(headerTwo));
                });

    }

    @Test
    void headerByRequest() {
    }
}
