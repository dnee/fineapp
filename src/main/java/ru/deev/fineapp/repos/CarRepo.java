package ru.deev.fineapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.deev.fineapp.domain.Car;

public interface CarRepo extends CrudRepository<Car, String> {
}
