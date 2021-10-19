package com.course.practicalJava.common;

import com.course.practicalJava.entity.Car;
import com.course.practicalJava.repository.CarElasticRepository;
import com.course.practicalJava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarElasticDataSource {


    private static final Logger LOG = LoggerFactory.getLogger(CarElasticDataSource.class);

    @Autowired
    private CarElasticRepository carElasticRepository;

    @Autowired
    //ponizej nie jest niezbedne dopoki nie mamy wiecej niz jednego beana o typie carService
    @Qualifier("randomCarService")
    private CarService carService;

    @Autowired
//uzywamy naszego beana webclientElasticSearch
    @Qualifier("webClientElasticSearch")
    private WebClient webClient;

    //execute on application startup
    @EventListener(ApplicationReadyEvent.class)

    public void populateData()
    {
        //usuwamy zawartosc odpalajac odpowiednie uri
        String response = webClient.delete().uri("/practical-java").retrieve().bodyToMono(String.class).block();
        LOG.info("End delete with response {}", response);

        //generate data
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            cars.add(carService.generateCar());
        }
        //save it to repo
        carElasticRepository.saveAll(cars);
        LOG.info("Saved {} cars", carElasticRepository.count());
    }

}
