package org.philipturbanovtz.database.repositories.measurement;

import org.philipturbanovtz.database.entities.measurement.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    Measurement getFirstByUserIdOrderByIdDesc(@Param("userId") Long userId);
}
