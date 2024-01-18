package com.example.AdventureAppraisals.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ItineraryDetails extends AbstractEntity {
    @OneToOne(mappedBy = "itineraryDetails")
    private Itinerary itinerary;


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

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }



    public Itinerary getItinerary() {
        return itinerary;
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
