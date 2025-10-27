package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.entity.SensorType;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    private final SensorReadingRepository sensorReadingRepository;

    public MeasurementService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public List<Double> getMeasurementValuesBySensorType(SensorType sensorType, LocalDateTime from, LocalDateTime to) {
        return sensorReadingRepository.findAllByTimestampIsBetweenAndSensorType(from, to, sensorType);
    }

    public Optional<Double> getAverageTemperature(LocalDateTime from, LocalDateTime to) {
        List<Double> sensorReadingValues = sensorReadingRepository.findAllByTimestampIsBetweenAndSensorType(from, to, SensorType.TEMPERATURE);

        if (sensorReadingValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(sensorReadingValues.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics()
                .getAverage());
    }

}
