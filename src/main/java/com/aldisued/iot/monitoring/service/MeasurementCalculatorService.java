package com.aldisued.iot.monitoring.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementCalculatorService {

    public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
        if (deviation < 0.0 || deviation > 1.0) {
            throw new IllegalArgumentException("Deviation must be between 0.0 and 1.0");
        }

        var summary = values.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();

        var average = summary.getAverage();

        var min = average - deviation * average;

        var max = average + deviation * average;

        return values.stream()
                .filter(value -> value >= min && value <= max)
                .toList();
    }

    public List<Double> getMovingAverage(List<Double> data, int windowSize) {
        if (windowSize <= 0 || data.size() < windowSize) {
            throw new IllegalArgumentException("Window size must be greater than 0");
        }

        List<Double> result = new ArrayList<>();

        int i = 0;
        while (i + windowSize <= data.size()) {
            result.addAll(data.stream()
                    .skip(i)
                    .limit(windowSize)
                    .reduce(Double::sum)
                    .map(sum -> sum / windowSize)
                    .stream()
                    .toList());
            i++;
        }

        return result;
    }

}
