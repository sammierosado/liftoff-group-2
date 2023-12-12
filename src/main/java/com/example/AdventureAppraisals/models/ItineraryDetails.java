package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ItineraryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fromCity;

    private String toCity;

    private LocalDateTime travelStartDateTime;

    private LocalDateTime travelEndDateTime;

    public ItineraryDetails() {}

    public ItineraryDetails(String fromCity, String toCity, LocalDateTime travelStartDateTime, LocalDateTime travelEndDateTime) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.travelStartDateTime = travelStartDateTime;
        this.travelEndDateTime = travelEndDateTime;
    }

    public int getId() {
        return id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public LocalDateTime getTravelStartDateTime() {
        return travelStartDateTime;
    }

    public void setTravelStartDateTime(LocalDateTime travelStartDateTime) {
        this.travelStartDateTime = travelStartDateTime;
    }

    public LocalDateTime getTravelEndDateTime() {
        return travelEndDateTime;
    }

    public void setTravelEndDateTime(LocalDateTime travelEndDateTime) {
        this.travelEndDateTime = travelEndDateTime;
    }
}
