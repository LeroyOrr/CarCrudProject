package com.leroycode.carcrudproject;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.exception.ResourceNotFoundException;
import com.leroycode.carcrudproject.mapper.CarMapper;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.repository.CarRepository;
import com.leroycode.carcrudproject.service.CarService;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarCrudProjectApplicationTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    private Car car;


    @Test
    public void saveCarTest() {

        CarRequest carRequest = Instancio.create(CarRequest.class);

        Car mappedCar = Instancio.create(Car.class);

        Car savedCar = Instancio.create(Car.class);

        when(carMapper.carRequestToCar(carRequest)).thenReturn(mappedCar);

        // Mock BOTH save() calls
        when(carRepository.save(mappedCar)).thenReturn(savedCar);
        when(carRepository.save(savedCar)).thenReturn(savedCar);

        // Act
        Car result = carService.saveCar(carRequest);

        // Assert
        assertNotNull(result);
        assertEquals(savedCar.getId(), result.getId());
        assertEquals(savedCar.getEngine(), result.getEngine());

        // Verify interactions
        verify(carMapper).carRequestToCar(carRequest);
        verify(carRepository).save(mappedCar);
        verify(carRepository).save(savedCar);
    }

    @Test
    public void getCarByIDTest() {
        long id = 1L;
        Car car = Instancio.create(Car.class);

        CarResponse carResponse = Instancio.create(CarResponse.class);

        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        when(carMapper.carToCarResponse(car)).thenReturn(carResponse);

        CarResponse result = carService.getCarById(id);

        assertNotNull(result);
        assertEquals(carResponse.id(), result.id());

        verify(carRepository).findById(id);
        verify(carMapper).carToCarResponse(car);
    }
    @Test
    void getCarByIDTestFail() {
        long id = 1L;

        when(carRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> carService.getCarById(id));

        verify(carRepository).findById(id);
        verify(carMapper, never()).carToCarResponse(any());
    }
    @Test
    void getAllCars() {
    List<Car> carList = Instancio.ofList(Car.class)
            .size(2)
            .set(Select.field(Car::getEngine), "engine1")
            .set(Select.field(Car::getTransmission), "transmission1")
            .set(Select.field(Car::getPads), "pads1")
            .set(Select.field(Car::getRotors), "rotors1")
            .set(Select.field(Car::getCalipers), "calipers1")
            .set(Select.field(Car::getShocks), "shocks1")
            .set(Select.field(Car::getStruts), "struts1")
            .set(Select.field(Car::getSteeringRacks), "steeringRacks1")
            .set(Select.field(Car::getControlArms), "controlArms1")
            .set(Select.field(Car::getBattery), "battery1")
            .set(Select.field(Car::getAlternator), "alternator1")
            .set(Select.field(Car::getStarter), "starter1")
            .set(Select.field(Car::getHeadlights), "headlights1")
            .set(Select.field(Car::getTailLights), "tailLights1")
            .set(Select.field(Car::getTurnSignalLights), "turnSignalLights1")
            .create();

    carList = List.of(
            carList.get(0),
            Instancio.of(Car.class)
            .set(Select.field(Car::getEngine), "engine2")
            .set(Select.field(Car::getTransmission), "transmission2")
            .set(Select.field(Car::getPads), "pads2")
            .set(Select.field(Car::getRotors), "rotors2")
            .set(Select.field(Car::getCalipers), "calipers2")
            .set(Select.field(Car::getShocks), "shocks2")
            .set(Select.field(Car::getStruts), "struts2")
            .set(Select.field(Car::getSteeringRacks), "steeringRacks2")
            .set(Select.field(Car::getControlArms), "controlArms2")
            .set(Select.field(Car::getBattery), "battery2")
            .set(Select.field(Car::getAlternator), "alternator2")
            .set(Select.field(Car::getStarter), "starter2")
            .set(Select.field(Car::getHeadlights), "headlights2")
            .set(Select.field(Car::getTailLights), "tailLights2")
            .set(Select.field(Car::getTurnSignalLights), "turnSignalLights2")
            .create()
    );

    List<CarResponse> carResponseList = List.of(
            Instancio.of(CarResponse.class)
                    .set(Select.field(CarResponse::id), 1L)
                    .set(Select.field(CarResponse::engine), "engine1")
                    .set(Select.field(CarResponse::transmission), "transmission1")
                    .set(Select.field(CarResponse::pads), "pads1")
                    .set(Select.field(CarResponse::rotors), "rotors1")
                    .set(Select.field(CarResponse::calipers), "calipers1")
                    .set(Select.field(CarResponse::shocks), "shocks1")
                    .set(Select.field(CarResponse::struts), "struts1")
                    .set(Select.field(CarResponse::steeringRacks), "steeringRacks1")
                    .set(Select.field(CarResponse::controlArms), "controlArms1")
                    .set(Select.field(CarResponse::battery), "battery1")
                    .set(Select.field(CarResponse::alternator), "alternator1")
                    .set(Select.field(CarResponse::starter), "starter1")
                    .set(Select.field(CarResponse::headlights), "headlights1")
                    .set(Select.field(CarResponse::tailLights), "tailLights1")
                    .set(Select.field(CarResponse::turnSignalLights), "turnSignalLights1")
                    .create(),

            Instancio.of(CarResponse.class)
                    .set(Select.field(CarResponse::id), 2L)
                    .set(Select.field(CarResponse::engine), "engine2")
                    .set(Select.field(CarResponse::transmission), "transmission2")
                    .set(Select.field(CarResponse::pads), "pads2")
                    .set(Select.field(CarResponse::rotors), "rotors2")
                    .set(Select.field(CarResponse::calipers), "calipers2")
                    .set(Select.field(CarResponse::shocks), "shocks2")
                    .set(Select.field(CarResponse::struts), "struts2")
                    .set(Select.field(CarResponse::steeringRacks), "steeringRacks2")
                    .set(Select.field(CarResponse::controlArms), "controlArms2")
                    .set(Select.field(CarResponse::battery), "battery2")
                    .set(Select.field(CarResponse::alternator), "alternator2")
                    .set(Select.field(CarResponse::starter), "starter2")
                    .set(Select.field(CarResponse::headlights), "headlights2")
                    .set(Select.field(CarResponse::tailLights), "tailLights2")
                    .set(Select.field(CarResponse::turnSignalLights), "turnSignalLights2")
                    .create()
    );

    // Mock behavior
    when(carRepository.findAll()).thenReturn(carList);
    when(carMapper.carRequestToCarResponse(carList)).thenReturn(carResponseList);

    // Execute
    List<CarResponse> result = carService.getAllCars();

    // Assertions
    assertNotNull(result);
    assertEquals("engine1", result.get(0).engine());
    assertEquals("engine2", result.get(1).engine());

    verify(carRepository).findAll();
    verify(carMapper).carRequestToCarResponse(carList);
}

    @Test
    public void deleteCarByIdTest() {
        long id = 1L;

        carService.deleteCarById(id);

        verify(carRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void updateCarByIdTest() {
        long id = 1L;
        Car car = Instancio.create(Car.class);

        Car savedCar = Instancio.create(Car.class);

        CarRequest carRequest = Instancio.create(CarRequest.class);

        CarResponse carResponse = Instancio.create(CarResponse.class);

        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        carMapper.updateCarFromCarRequest(carRequest, car);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.carToCarResponse(savedCar)).thenReturn(carResponse);

        CarResponse result = carService.updateCarById(id,carRequest);
        assertNotNull(result);
        assertEquals(carResponse.engine(), result.engine());
        assertEquals(carResponse.alternator(), result.alternator());

        verify(carRepository).findById(id);
        verify(carRepository).save(car);
    }

}
