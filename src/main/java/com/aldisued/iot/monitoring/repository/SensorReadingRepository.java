package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    @Query("""
            select sr.value from SensorReading sr
            where sr.sensor.type = :sensorType
            and sr.timestamp >= :from
            and sr.timestamp <= :to
            order by sr.timestamp
            """
    )
    List<Double> findAllByTimestampIsBetweenAndSensorType(LocalDateTime from, LocalDateTime to, SensorType sensorType);
}
