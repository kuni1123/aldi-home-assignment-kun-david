package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.SensorDto;
import com.aldisued.iot.monitoring.entity.Sensor;
import com.aldisued.iot.monitoring.exception.EntityConflictException;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor saveSensor(SensorDto sensor) {
        try {
            return sensorRepository.save(new Sensor(
                    sensor.name(),
                    sensor.type()
            ));
        } catch (DataIntegrityViolationException e) {
            throw new EntityConflictException(1002, e.getMessage());
        }
    }
}
