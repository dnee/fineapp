package ru.deev.fineapp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.deev.fineapp.domain.FineHistory;

public interface FineHistoryRepo extends CrudRepository<FineHistory, Long> {
    Iterable<FineHistory> getFineHistoriesByCar_LicensePlate(String licensePlate);

    Iterable<FineHistory> getFineHistoriesByCar_Owner_DriverId(String driverId);

    Long countFineHistoriesByFine_FineId(Long fineId);
}
