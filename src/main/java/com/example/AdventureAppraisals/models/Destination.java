package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity{

    @OneToMany(mappedBy = "destination")
    private final List<Itinerary> itinerary = new ArrayList<>();
    public Destination() {}

    public List<Itinerary> getItinerary() {
        return itinerary;
    }


}
