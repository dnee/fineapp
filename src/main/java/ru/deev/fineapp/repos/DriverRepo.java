package ru.deev.fineapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.deev.fineapp.domain.Driver;

public interface DriverRepo extends CrudRepository<Driver, String> {
}
