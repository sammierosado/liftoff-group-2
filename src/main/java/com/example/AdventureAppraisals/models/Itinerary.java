package com.example.AdventureAppraisals.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.springframework.validation.annotation.Validated;

import java.util.Iterator;
import java.util.List;

@Entity
public class Itinerary extends AbstractEntity {

    @ManyToOne
    private Destination destination;
    @OneToOne(cascade = CascadeType.ALL)
    private ItineraryDetails itineraryDetails;

    public Destination getDestination() {
        return destination;
    }

    public ItineraryDetails getItineraryDetails() {
        return itineraryDetails;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setItineraryDetails(ItineraryDetails itineraryDetails) {
        this.itineraryDetails = itineraryDetails;
    }

    public Itinerary() {};


}
