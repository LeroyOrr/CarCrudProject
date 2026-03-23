package com.leroycode.carcrudproject.service;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.exception.ResourceNotFoundException;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //Saving car request to database
    public Car saveCar(CarRequest carRequest) {
        Car car = new Car(carRequest.engine(), carRequest.transmission(),carRequest.pads(), carRequest.rotors(),
                carRequest.calipers(), carRequest.shocks(), carRequest.struts(), carRequest.steeringRacks(),
                carRequest.controlArms(), carRequest.battery(), carRequest.alternator(), carRequest.starter(),
                carRequest.headlights(), carRequest.tailLights(), carRequest.turnSignalLights());
       Car savedCar = carRepository.save(car);
       return savedCar;
    }

    public CarResponse getCarById(long id) {
        Optional<Car> data = carRepository.findById(id);
        Car car = null;
        if (data.isPresent()) {
            car = data.get();
        }
        else {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        CarResponse carResponse = new CarResponse(car.getId(),car.getEngine(), car.getTransmission(), car.getPads(), car.getRotors(),
                car.getCalipers(), car.getShocks(), car.getStruts(), car.getSteeringRacks(),
                car.getControlArms(), car.getBattery(), car.getAlternator(), car.getStarter(),
                car.getHeadlights(), car.getTailLights(), car.getTurnSignalLights());

        return carResponse;
    }
    // Trying to get all cars
    public List<CarResponse> getAllCars() {
        //Getting all the cars from the Car class, using .findAll method from CRUD
        List<Car> allCars = carRepository.findAll();
        List<CarResponse> carResponseList = new ArrayList<>();
        for (Car car : allCars) {
            carResponseList.add(new CarResponse(car.getId(),car.getEngine(), car.getTransmission(), car.getPads(), car.getRotors(),
                    car.getCalipers(), car.getShocks(), car.getStruts(), car.getSteeringRacks(),
                    car.getControlArms(), car.getBattery(), car.getAlternator(), car.getStarter(),
                    car.getHeadlights(), car.getTailLights(), car.getTurnSignalLights()));
        }
        return carResponseList;

    }
    public void deleteCarById(long id) {
        carRepository.deleteById(id);

    }

    public CarResponse updateCarById(long id, CarRequest carRequest) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car ID is not found!"));
        car.setEngine(carRequest.engine());
        car.setTransmission(carRequest.transmission());
        car.setPads(carRequest.pads());
        car.setRotors(carRequest.rotors());
        car.setCalipers(carRequest.calipers());
        car.setShocks(carRequest.shocks());
        car.setStruts(carRequest.struts());
        car.setSteeringRacks(carRequest.steeringRacks());
        car.setControlArms(carRequest.controlArms());
        car.setBattery(carRequest.battery());
        car.setAlternator(carRequest.alternator());
        car.setStarter(carRequest.starter());
        car.setHeadlights(carRequest.headlights());
        car.setTailLights(carRequest.tailLights());
        car.setTurnSignalLights(carRequest.turnSignalLights());
        Car savedCar =carRepository.save(car);
        return new CarResponse(savedCar.getId(), savedCar.getEngine(), savedCar.getTransmission(), savedCar.getPads(),
                savedCar.getRotors(), savedCar.getCalipers(), savedCar.getShocks(), savedCar.getStruts(),
                savedCar.getSteeringRacks(), savedCar.getControlArms(), savedCar.getBattery(), savedCar.getAlternator(),
                savedCar.getStarter(), savedCar.getHeadlights(), savedCar.getTailLights(),
                savedCar.getTurnSignalLights());



    }
}
