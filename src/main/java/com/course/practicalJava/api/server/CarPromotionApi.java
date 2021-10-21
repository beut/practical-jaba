package com.course.practicalJava.api.server;

import com.course.practicalJava.entity.CarPromotion;
import com.course.practicalJava.exception.IllegalApiParamException;
import com.course.practicalJava.repository.CarPromotionElasticRepository;
import com.course.practicalJava.service.CarPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarPromotionApi {

    @Autowired
    CarPromotionElasticRepository carPromotionElasticRepository;

    @Autowired
    CarPromotionService carPromotionService;


    @GetMapping(value = "/promotions")
    public List<CarPromotion> listAvailablePromotions(@RequestParam(name = "type") String promotionType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        if (!carPromotionService.isValidPromotionType(promotionType)) {
            throw new IllegalApiParamException("invalid promo type " + promotionType);
        } else {
            Pageable pageable = PageRequest.of(page, size);
            return carPromotionElasticRepository.findPromotionByType(promotionType, pageable).getContent();
        }

    }

}