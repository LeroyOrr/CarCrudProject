package com.leroycode.carcrudproject;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.exception.ResourceNotFoundException;
import com.leroycode.carcrudproject.mapper.CarMapper;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import com.leroycode.carcrudproject.repository.CarRepository;
import com.leroycode.carcrudproject.service.CarService;
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

        CarRequest carRequest = new CarRequest("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        Car mappedCar = new Car("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        Car savedCar = new Car("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        CarResponse carResponse = new CarResponse(1L, "engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        when(carMapper.carRequestToCar(carRequest)).thenReturn(mappedCar);

        // Mock BOTH save() calls
        when(carRepository.save(mappedCar)).thenReturn(savedCar);
        when(carRepository.save(savedCar)).thenReturn(savedCar);

        // Act
        Car result = carService.saveCar(carRequest);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        assertEquals("engine", result.getEngine());

        // Verify interactions
        verify(carMapper).carRequestToCar(carRequest);
        verify(carRepository).save(mappedCar);
        verify(carRepository).save(savedCar);
    }

    @Test
    public void getCarByIDTest() {
        long id = 1L;
        Car car = new Car("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        CarResponse carResponse = new CarResponse(1L, "engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        when(carMapper.carToCarResponse(car)).thenReturn(carResponse);

        CarResponse result = carService.getCarById(id);

        assertNotNull(result);
        assertEquals(1L, result.id());

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
    public void getAllCarsTest() {
        List<Car> carList = List.of(new Car("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights"),

                new Car("engine2", "transmission2", "pads2",
                "rotors2", "calipers2", "shocks2", "struts2",
                "steeringRacks2", "controlArms2", "battery2", "alternator2",
                "starter2", "headlights2", "tailLights2", "turnSignalLights2"));

        List<CarResponse> carResponseList = List.of(
                new CarResponse(1L, "engine1", "transmission1", "pads1",
                        "rotors1", "calipers1", "shocks1", "struts1",
                        "steeringRacks1", "controlArms1", "battery1", "alternator1",
                        "starter1", "headlights1", "tailLights1", "turnSignalLights1"),

                new CarResponse(2L, "engine2", "transmission2", "pads2",
                        "rotors2", "calipers2", "shocks2", "struts2",
                        "steeringRacks2", "controlArms2", "battery2", "alternator2",
                        "starter2", "headlights2", "tailLights2", "turnSignalLights2")
        );
        when(carRepository.findAll()).thenReturn(carList);
        when(carMapper.carRequestToCarResponse(carList)).thenReturn(carResponseList);

        List<CarResponse> result = carService.getAllCars();

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
        Car car = new Car("engine", "transmission", "pads",
                "rotors", "calipers", "shocks", "struts",
                "steeringRacks", "controlArms", "battery", "alternator",
                "starter", "headlights", "tailLights", "turnSignalLights");

        Car savedCar = new Car("engine2", "transmission2", "pads2",
                "rotors2", "calipers2", "shocks2", "struts2",
                "steeringRacks2", "controlArms2", "battery2", "alternator2",
                "starter2", "headlights2", "tailLights2", "turnSignalLights2");

        CarRequest carRequest = new CarRequest("engine2", "transmission2", "pads2",
                "rotors2", "calipers2", "shocks2", "struts2",
                "steeringRacks2", "controlArms2", "battery2", "alternator2",
                "starter2", "headlights2", "tailLights2", "turnSignalLights2");

        CarResponse carResponse = new CarResponse(1L,"engine2", "transmission2", "pads2",
                "rotors2", "calipers2", "shocks2", "struts2",
                "steeringRacks2", "controlArms2", "battery2", "alternator2",
                "starter2", "headlights2", "tailLights2", "turnSignalLights2");

        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        carMapper.updateCarFromCarRequest(carRequest, car);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.carToCarResponse(savedCar)).thenReturn(carResponse);

        CarResponse result = carService.updateCarById(id,carRequest);
        assertNotNull(result);
        assertEquals("engine2", result.engine());
        assertEquals("alternator2", result.alternator());

        verify(carRepository).findById(id);
        verify(carRepository).save(car);
    }


}
