package com.course.practicalJava.repository;

import com.course.practicalJava.entity.CarPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPromotionElasticRepository extends ElasticsearchRepository<CarPromotion, String> {

    Page<CarPromotion> findPromotionByType(String promotionType, Pageable pageable);
}
