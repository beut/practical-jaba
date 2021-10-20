package com.course.practicalJava.repository;

import com.course.practicalJava.entity.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// wrzucamy do bazy elementy typu Car, string jest type of identifier field
@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

    public List<Car> findByBrandAndColor(String brand, String color );

    public List<Car> findByFirstReleaseDateAfter(LocalDate date);
}
