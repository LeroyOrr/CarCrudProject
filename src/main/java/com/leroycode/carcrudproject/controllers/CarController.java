package com.leroycode.carcrudproject.controllers;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService =carService;
    }
    //Create
    @PostMapping("/cars")
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {

        Car car = carService.saveCar(carRequest);
        CarResponse carResponse = new CarResponse(car.getId(),car.getEngine(), car.getTransmission(), car.getPads(), car.getRotors(),
                car.getCalipers(), car.getShocks(), car.getStruts(), car.getSteeringRacks(),
                car.getControlArms(), car.getBattery(), car.getAlternator(), car.getStarter(),
                car.getHeadlights(), car.getTailLights(), car.getTurnSignalLights());
        return ResponseEntity.created(URI.create("/api/v1/cars/" + car.getId())).body(carResponse);
    }
    //Read
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable long id) {
        CarResponse carResponse = carService.getCarById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }
    //Read
    @GetMapping("/allcars")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        //This is going to deal with CarReposiory, since it holds all of the data members
        List<CarResponse> carResponse = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }
    //Delete
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar (@PathVariable long id) {
        carService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    //Update
    @PutMapping("/cars/{id}") //updating the car by id
    //need the id, and CarRequest, it has all the info of the car
    public ResponseEntity<CarResponse> updateCar(@PathVariable long id, @Valid @RequestBody CarRequest carRequest){
        //creating a new response that is updating the car
        CarResponse carResponse = carService.updateCarById(id, carRequest);
        //return a good http response and the carresponse created
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }

}
