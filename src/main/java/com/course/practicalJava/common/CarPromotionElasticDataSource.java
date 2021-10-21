package com.course.practicalJava.common;

import com.course.practicalJava.entity.CarPromotion;
import com.course.practicalJava.repository.CarPromotionElasticRepository;
import com.course.practicalJava.service.CarPromotionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarPromotionElasticDataSource {


    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CarPromotionElasticDataSource.class);

    @Autowired
    CarPromotionElasticRepository carPromotionElasticRepository;

    @Autowired
    CarPromotionService carPromotionService;

    @EventListener(ApplicationReadyEvent.class)
    public void populateData()
    {
        carPromotionElasticRepository.deleteAll();

        List<CarPromotion> carPromotions = new ArrayList<>();
        CarPromotion carPromotion1 = new CarPromotion("bonus", "Purchase in cash and get free dinner");
        CarPromotion carPromotion2 = new CarPromotion("bonus", "Purchase luxury car and get free trip");
        CarPromotion carPromotion3 = new CarPromotion("bonus", "Purchase 3 luxury cars and get a bar of gold");
        CarPromotion carPromotion4 = new CarPromotion("discount", "Purchase in cash and 1% discount");
        CarPromotion carPromotion5 = new CarPromotion("discount", "Purchase before end of month and get 1.5%");
        CarPromotion carPromotion6 = new CarPromotion("discount", "Today only we give 0.5% additional discount");

        carPromotions.add(carPromotion1);
        carPromotions.add(carPromotion2);
        carPromotions.add(carPromotion3);
        carPromotions.add(carPromotion4);
        carPromotions.add(carPromotion5);
        carPromotions.add(carPromotion6);

        carPromotionElasticRepository.saveAll(carPromotions);
        LOG.info("carPromotion data saved: {}", carPromotionElasticRepository.count()  );


    }


}
