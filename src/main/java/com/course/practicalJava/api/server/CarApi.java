package com.course.practicalJava.api.server;

import com.course.practicalJava.api.response.ErrorResponse;
import com.course.practicalJava.entity.Car;
import com.course.practicalJava.exception.IllegalApiParamException;
import com.course.practicalJava.repository.CarElasticRepository;
import com.course.practicalJava.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping(value = "/api/car/v1")
public class CarApi {

    private static final Logger LOG = LoggerFactory.getLogger(CarApi.class);
    @Autowired
    private CarService carService;

    @Autowired
    private CarElasticRepository carElasticRepository;

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Car random() {
        return carService.generateCar();
    }

    @PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echo(@RequestBody Car car) {
        LOG.info("Car is {}", car);
        return car.toString();
    }

    @GetMapping(value = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> randomCars() {
        List<Car> result = new ArrayList<>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
            result.add(carService.generateCar());
        }
        return result;

    }

    @GetMapping(value = "/count")
    public String countCars() {
        return "There are: " + carElasticRepository.count() + " cars in ES";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCar(@RequestBody Car car) {
        String id = carElasticRepository.save(car).getId();

        return "Saved with id: " +id;
    }

    @GetMapping(value = "/{id}")
    public Car getCar(@PathVariable("id") String carId) {

        return carElasticRepository.findById(carId).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public String updateCar(@PathVariable("id") String carId, @RequestBody Car updatedCar) {

        updatedCar.setId(carId);
        String id = carElasticRepository.save(updatedCar).getId();
        return "updated car with id "+id;

    }

    @GetMapping(value = "/find-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> findByBrandAndColor(@RequestBody Car car, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        //sorting by price added
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "price"));
        return carElasticRepository.findByBrandAndColor(car.getBrand(), car.getColor(), pageable).getContent();
    }

    @GetMapping(value = "/cars/{brand}/{color}")
    public ResponseEntity<Object> findCarsByPath(@PathVariable String brand, @PathVariable String color, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SERVER, "Spring");
        headers.add("X-Custom-Header", "Custom header response");



        //jesli nieprawidlowy kolor -> zwroc http 400
        if (StringUtils.isNumeric(color)) {
            ErrorResponse errorResponse = new ErrorResponse("invalid color "+color, LocalDateTime.now());
            return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
        }

        PageRequest pageable = PageRequest.of(page, size);
        //getContent -> z Page robi List
        List<Car> cars = carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
        return ResponseEntity.ok().headers(headers).body(cars);
    }

    @GetMapping(value = "/cars")
    public List<Car> findCarByParam(@RequestParam String brand, @RequestParam String color, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {


        if (StringUtils.isNumeric(color)) {
            throw new IllegalArgumentException("invalid color " + color);


        }

        if (StringUtils.isNumeric(brand)) {

            throw new IllegalApiParamException("invalid brand: " + brand);
        }


        PageRequest pageable = PageRequest.of(page, size);
        return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();

    }


    //dane w params
    @GetMapping(value = "/cars/date")
    public List<Car> findCarsReleasedAfter(@RequestParam(name = "first_release_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstReleaseDate) {
        return carElasticRepository.findByFirstReleaseDateAfter(firstReleaseDate);

    }


}
