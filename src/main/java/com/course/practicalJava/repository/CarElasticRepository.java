package com.course.practicalJava.repository;

import com.course.practicalJava.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// wrzucamy do bazy elementy typu Car, string jest type of identifier field
@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

     Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);

     List<Car> findByFirstReleaseDateAfter(LocalDate date);
}
