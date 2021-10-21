package com.course.practicalJava.api.server;

import com.course.practicalJava.entity.Car;
import com.course.practicalJava.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarApiTest {

    @Autowired
    WebTestClient webTestClient;


@Test
    void random() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Testing loop " + i);
            webTestClient.get().uri("/api/car/v1/random").exchange().expectBody(Car.class).value(
                    car -> {
                        assert (CarService.BRANDS.contains(car.getBrand()));
                        assert (CarService.COLORS.contains(car.getColor()));
                    });
        }
    }


    //@Test
    void randomCars() {

    }

  //  @Test
    void countCars() {
    }

@Autowired
@Qualifier("randomCarService")
private CarService carService;

    @Test
    void saveCar() {

        Car car = carService.generateCar();
//Tworzymy posta i sprawdzamy czy wykona sie w ciagu sekundy

        for (int i = 0; i< 100; i++) {
            Assertions.assertTimeout(Duration.ofSeconds(1),
                    () -> webTestClient.post().uri("/api/car/v1").contentType(MediaType.APPLICATION_JSON).bodyValue(car).exchange()
                            .expectStatus().is2xxSuccessful());
        }

    }

 //   @Test
    void getCar() {
    }

 //   @Test
    void updateCar() {
    }

  //  @Test
    void findByBrandAndColor() {
    }

  //  @Test
    void findCarsByPath() {
    }

    @Test
    void findCarByParam() {
        final int size = 5;

        for (String brand: CarService.BRANDS) {
            for (String color: CarService.COLORS) {
                webTestClient.get().uri(uriBuilder -> uriBuilder.path("/api/car/v1/cars").queryParam("brand", brand)
                        .queryParam("color", color).queryParam("page", 0).queryParam("size", size).build())
                        .exchange().expectBodyList(Car.class).value(
                                cars -> {
                                    Assertions.assertNotNull(cars);
                                    Assertions.assertTrue(cars.size() <= size);
                                });
            }

        }




    }

    //@Test
    void findCarsReleasedAfter() {
    }
}