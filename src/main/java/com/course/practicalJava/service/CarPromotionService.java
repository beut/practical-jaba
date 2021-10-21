package com.course.practicalJava.service;

import com.course.practicalJava.entity.CarPromotion;

import java.util.List;

public interface CarPromotionService {

    List<String> PROMOTION_TYPES = List.of("bonus", "discount");

    boolean isValidPromotionType(String promotionType);



}
