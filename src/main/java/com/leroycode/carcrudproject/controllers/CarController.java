package com.leroycode.carcrudproject.controllers;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService =carService;
    }
    @PostMapping("/cars")
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {

        Car car = carService.saveCar(carRequest);
        CarResponse carResponse = new CarResponse(car.getId(),car.getEngine(), car.getTransmission(), car.getPads(), car.getRotors(),
                car.getCalipers(), car.getShocks(), car.getStruts(), car.getSteeringRacks(),
                car.getControlArms(), car.getBattery(), car.getAlternator(), car.getStarter(),
                car.getHeadlights(), car.getTailLights(), car.getTurnSignalLights());
        return ResponseEntity.created(URI.create("/api/v1/cars/" + car.getId())).body(carResponse);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable long id) {
        CarResponse carResponse = carService.getCarById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }

}
