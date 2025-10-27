package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, String> {


    @Query("""
                select a
                from Alert a
                left join fetch a.sensor
                where a.sensor.id = :sensorId
                order by a.timestamp desc
                limit 1
            """)
    Optional<Alert> findLastOneBySensorId(UUID sensorId);
}
