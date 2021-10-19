package com.course.practicalJava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.elasticsearch.client.ml.job.config.DataDescription;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Document(indexName = "practical-java")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

@Id
    private String id;
    private String brand;
    private String color;
    private String type;
    private int price;
    private boolean available;
    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="europe/london")
    private LocalDate firstReleaseDate;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> additionalFeatures;
    @JsonUnwrapped
    private Engine engine;
    private List<Tire> tires;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String secretFeature;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", firstReleaseDate=" + firstReleaseDate +
                ", additionalFeatures=" + additionalFeatures +
                ", engine=" + engine +
                ", tires=" + tires +
                ", secretFeature='" + secretFeature + '\'' +
                '}';
    }

    public Engine getEngine() {
        return engine;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecretFeature() {
        return secretFeature;
    }

    public void setSecretFeature(String secretFeature) {
        this.secretFeature = secretFeature;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    public List<String> getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(List<String> additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car(String brand, String color, String type) {
        this.brand = brand;
        this.color = color;
        this.type = type;
    }

    public Car() {
    }

    public Car(String brand, String color, String type, int price, boolean available, LocalDate firstReleaseDate) {
        this.brand = brand;
        this.color = color;
        this.type = type;
        this.price = price;
        this.available = available;
        this.firstReleaseDate = firstReleaseDate;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(LocalDate firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }
}
