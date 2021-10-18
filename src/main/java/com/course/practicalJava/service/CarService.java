package com.course.practicalJava.service;

import com.course.practicalJava.entity.Car;

import java.util.List;

public interface CarService {

    List<String> BRANDS = List.of("Toyota", "Honda", "Ford");
    List<String> COLORS = List.of("Red", "Black", "White");
    List<String> TYPES = List.of("Sedan", "SUV", "MPV");


    List<String> ADDITIONAL_FEATURES = List.of("GPS", "Alarm", "Sunroof", "Media player", "Leather seats");


    Car generateCar();

}
