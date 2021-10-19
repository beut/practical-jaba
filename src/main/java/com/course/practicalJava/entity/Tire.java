package com.course.practicalJava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tire {
    private String manufacturer;
    @JsonProperty(value="diameter")
    private int  size;
    @JsonIgnore
    private int prize;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "manufacturer='" + manufacturer + '\'' +
                ", size=" + size +
                ", prize=" + prize +
                '}';
    }

    public Tire(String manufacturer, int size, int prize) {
        this.manufacturer = manufacturer;
        this.size = size;
        this.prize = prize;
    }

    public Tire() {
    }
}
