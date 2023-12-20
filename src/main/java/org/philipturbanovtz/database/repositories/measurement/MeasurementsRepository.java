package org.philipturbanovtz.database.repositories.measurement;

import org.philipturbanovtz.database.entities.measurement.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    Measurement getFirstByUserIdOrderByIdDesc(@Param("userId") Long userId);

    @Query(
        "SELECT m FROM Measurement m" +
            " WHERE m.user.id = :userId" +
            " AND (:dateFrom IS NULL OR (m.createdAtMs >= :dateFrom))" +
            " AND (:dateTo IS NULL OR (m.createdAtMs <= :dateTo))" +
            " ORDER BY m.id DESC"
    )
    Page<Measurement> getMeasurementsHistoryForUser(
        @Param("dateFrom") Long dateFrom,
        @Param("dateTo") Long dateTo,
        @Param("userId") Long userId,
        Pageable pageable
    );
}
