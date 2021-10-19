package com.course.practicalJava.repository;

import com.course.practicalJava.entity.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// wrzucamy do bazy elementy typu Car, string jest type of identifier field
@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {


}
