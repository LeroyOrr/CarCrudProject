package com.leroycode.carcrudproject;

import com.leroycode.carcrudproject.entity.Car;
import com.leroycode.carcrudproject.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class CarCrudRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void testPersistAndFindCarUsingEntityManager() {
        // Arrange: create a Car entity
        Car car = Instancio.of(Car.class)
                .ignore(Select.field("id"))
                .set(Select.field(Car::getEngine), "engine")
                .set(Select.field(Car::getTransmission), "transmission")
                .create();

        // Persist using EntityManager
        entityManager.persist(car);
        entityManager.flush(); // forces SQL execution

        // Act: retrieve using the repository
        Car found = carRepository.findById(car.getId()).orElse(null);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getEngine()).isEqualTo("engine");
        assertThat(found.getTransmission()).isEqualTo("transmission");
    }
}
