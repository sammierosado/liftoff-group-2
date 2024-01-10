package com.example.AdventureAppraisals.models;


import jakarta.persistence.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Itinerary extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Destination destination;
    @OneToOne(cascade = CascadeType.ALL)
    private ItineraryDetails itineraryDetails;

    @OneToMany(mappedBy = "itinerary")
    private final List<Review> reviews = new ArrayList<>();

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

    public Itinerary() {}

    public List<Review> getReviews() {
        return reviews;
    }
}
