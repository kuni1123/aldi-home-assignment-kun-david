package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.entity.Alert;
import com.aldisued.iot.monitoring.exception.EntityNotFoundException;
import com.aldisued.iot.monitoring.repository.AlertRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final SensorRepository sensorRepository;
    private final KafkaTemplate<String, AlertDto> kafkaTemplate;

    public AlertService(AlertRepository alertRepository, SensorRepository sensorRepository,
                        KafkaTemplate<String, AlertDto> kafkaTemplate) {
        this.alertRepository = alertRepository;
        this.sensorRepository = sensorRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Alert saveAlert(AlertDto alertDto) {
        // TODO: Task 6
        return null;
    }

    public AlertDto findLastAlertBySensorId(UUID sensorId) {
        return alertRepository.findLastOneBySensorId(sensorId)
                .map(alert -> new AlertDto(alert.getSensor().getId(), alert.getMessage(), alert.getTimestamp()))
                .orElseThrow(() -> new EntityNotFoundException(2001, String.format("No alert found for sensor with id %s.", sensorId)));
    }
}
