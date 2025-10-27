package com.aldisued.iot.monitoring.service;


import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
      if (deviation < 0.0 || deviation > 1.0) {
          throw new IllegalArgumentException("Deviation must be between 0.0 and 1.0");
      }

      var average = values.stream()
              .reduce(Double::sum)
              .map(sum -> sum / values.size());

      var min = average
              .map(value -> value - deviation * value)
              .orElse(0.0);

      var max = average
              .map(value -> value + deviation * value)
              .orElse(1.0);

    return values.stream().filter(value -> value >= min && value <= max).toList();
  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

}
