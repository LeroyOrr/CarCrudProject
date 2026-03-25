package com.leroycode.carcrudproject;

import com.leroycode.carcrudproject.entity.Car;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarCrudProjectApplicationTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;


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

}
