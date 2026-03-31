package com.leroycode.carcrudproject.mapper;
import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.models.CarRequest;
import com.leroycode.carcrudproject.models.CarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring") //Utilizing MapStruct
public interface CarMapper {

    // CarRequest -> Car (for saving)
    Car carRequestToCar(CarRequest carRequest);

    //CarResponse -> Car (for returning to client)
    CarResponse carToCarResponse(Car car);

    //List<CarResponse> -> List<Car> (get all cars)
    List<CarResponse> carRequestToCarResponse(List<Car> cars);

    void updateCarFromCarRequest(CarRequest carRequest, @MappingTarget Car car);


}
