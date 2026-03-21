package com.leroycode.carcrudproject.repository;

import com.leroycode.carcrudproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
