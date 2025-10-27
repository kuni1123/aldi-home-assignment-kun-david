package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.SensorReadingDto;
import com.aldisued.iot.monitoring.entity.Sensor;
import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.exception.EntityNotFoundException;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorReadingService {

    private final SensorReadingRepository sensorReadingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingService(SensorReadingRepository sensorReadingRepository,
                                SensorRepository sensorRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.sensorRepository = sensorRepository;
    }

    public SensorReading saveSensorReading(SensorReadingDto sensorReadingDto) {
        Sensor sensor = this.sensorRepository.findById(sensorReadingDto.sensorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        1001,
                        String.format("Sensor not found with id %s", sensorReadingDto.sensorId())
                ));

        return this.sensorReadingRepository.save(new SensorReading(sensorReadingDto.value(), sensorReadingDto.timestamp(), sensor));
    }

}
