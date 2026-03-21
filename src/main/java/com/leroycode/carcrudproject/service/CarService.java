package com.leroycode.carcrudproject.service;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.exception.ResourceNotFoundException;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

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
        CarResponse carResponse = new CarResponse(car.getEngine(), car.getTransmission(), car.getPads(), car.getRotors(),
                car.getCalipers(), car.getShocks(), car.getStruts(), car.getSteeringRacks(),
                car.getControlArms(), car.getBattery(), car.getAlternator(), car.getStarter(),
                car.getHeadlights(), car.getTailLights(), car.getTurnSignalLights());

        return carResponse;
    }
}
