package com.leroycode.carcrudproject.service;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.exception.ResourceNotFoundException;
import com.leroycode.carcrudproject.mapper.CarMapper;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    //Saving car request to database
    public Car saveCar(CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        Car savedCar = carRepository.save(car);
       return carRepository.save(savedCar);
    }

    public CarResponse getCarById(long id) {
        Car car = carRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + "not Found"));
        CarResponse carResponse = carMapper.carToCarResponse(car);
        return carResponse;
    }
    // Trying to get all cars
    public List<CarResponse> getAllCars() {
        List<Car> allCars = carRepository.findAll();
        List<CarResponse> carResponseList = carMapper.carRequestToCarResponse(allCars);
        return carResponseList;

    }
    public void deleteCarById(long id) {
        carRepository.deleteById(id);

    }

    public CarResponse updateCarById(long id, CarRequest carRequest) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car ID is not found!"));
        carMapper.updateCarFromCarRequest(carRequest, car);
        Car savedCar = carRepository.save(car);

        return carMapper.carToCarResponse(savedCar);



    }
}
