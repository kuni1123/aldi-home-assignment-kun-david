package com.aldisued.iot.monitoring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "alerts")
@Entity
public class Alert {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String message;

  @Column(nullable = false)
  private LocalDateTime timestamp;

  public Alert() {
  }

  public Alert(
      String message,
      LocalDateTime timestamp,
      Sensor sensor
  ) {
    this.message = message;
    this.timestamp = timestamp;
    //TODO: Task 2
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Sensor getSensor() {
    //TODO: Task 2
    return null;
  }

  public void setSensor(Sensor sensor) {
    //TODO: Task 2
  }
}
