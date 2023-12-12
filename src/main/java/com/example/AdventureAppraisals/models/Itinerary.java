package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int itineraryDetailsId;

    private int destinationId;

    public Itinerary() {};

    public Itinerary(String name, int itineraryDetailsId, int destinationId) {
        this.name = name;
        this.itineraryDetailsId = itineraryDetailsId;
        this.destinationId = destinationId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
