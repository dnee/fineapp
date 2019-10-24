package ru.deev.fineapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.deev.fineapp.domain.Fine;

public interface FineRepo extends CrudRepository<Fine, Long> {
}
