package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;

@Entity
public class Itinerary extends AbstractEntity{

    private int itineraryDetailsId;

    private int destinationId;

    public Itinerary() {};

    public Itinerary(int itineraryDetailsId, int destinationId) {
        this.itineraryDetailsId = itineraryDetailsId;
        this.destinationId = destinationId;
    }

    public int getItineraryDetails() {
        return itineraryDetailsId;
    }

    public void setItineraryDetails(int itineraryDetailsId) {
        this.itineraryDetailsId = itineraryDetailsId;
    }

    public int getDestination() {
        return destinationId;
    }

    public void setDestination(int destinationId) {
        this.destinationId = destinationId;
    }
}
