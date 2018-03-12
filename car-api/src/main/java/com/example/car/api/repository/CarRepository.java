package com.example.car.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.car.api.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
