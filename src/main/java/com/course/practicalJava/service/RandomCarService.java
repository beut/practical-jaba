package com.course.practicalJava.service;

import com.course.practicalJava.entity.Car;
import com.course.practicalJava.entity.Engine;
import com.course.practicalJava.entity.Tire;
import com.course.practicalJava.util.RandomDateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        int randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURES.size());

        List<String> additionalFeatures = new ArrayList<>();

        for (int i = 0; i < randomCount; i++) {
            additionalFeatures.add(ADDITIONAL_FEATURES.get(i));
        }

        String fuel = FUELS.get(ThreadLocalRandom.current().nextInt(0, FUELS.size()));
        int horsePower = ThreadLocalRandom.current().nextInt(100, 220);

        Engine engine = new Engine(fuel, horsePower);


        List<Tire> tires = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Tire tire = new Tire();
            tire.setManufacturer(TIRE_MANUFACTURERS.get(ThreadLocalRandom.current().nextInt(0, TIRE_MANUFACTURERS.size())));
            tire.setSize(ThreadLocalRandom.current().nextInt(15, 18));
            tire.setPrize(ThreadLocalRandom.current().nextInt(200, 401));
            tires.add(tire);
        }

        Car result = new Car(brand, color, type);
        result.setAvailable(available);
        result.setFirstReleaseDate(firstReleaseDate);
        result.setPrice(price);
        result.setAdditionalFeatures(additionalFeatures);
        result.setEngine(engine);
        result.setTires(tires);

        String secretFeature = ThreadLocalRandom.current().nextBoolean() ? "Can fly" : null;
        result.setSecretFeature(secretFeature);

        return result;
    }
}
