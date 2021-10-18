package com.course.practicalJava.service;

import com.course.practicalJava.entity.Car;
import com.course.practicalJava.util.RandomDateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCarService implements CarService {

    @Override
    public Car generateCar() {

        String brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
        String color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
        String type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));

        Boolean available = ThreadLocalRandom.current().nextBoolean();
        int price = ThreadLocalRandom.current().nextInt(5000, 12001);
        LocalDate firstReleaseDate = RandomDateUtil.generateRandomLocalDate();



        Car result =  new Car(brand, color, type);
        result.setAvailable(available);
        result.setFirstReleaseDate(firstReleaseDate);
        result.setPrice(price);
        return result;
    }
}
