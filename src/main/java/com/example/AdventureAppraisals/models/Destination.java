package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity{

    private String destinationPicUrl;
    @OneToMany(mappedBy = "destination")
    private final List<Itinerary> itinerary = new ArrayList<>();
    public Destination() {}
    public Destination(String destinationPicUrl){
        this.destinationPicUrl = destinationPicUrl;
    }

    public List<Itinerary> getItinerary() {
        return itinerary;
    }

    public String getDestinationPicUrl() {
        return destinationPicUrl;
    }

    public void setDestinationPicUrl(String destinationPicUrl) {
        this.destinationPicUrl = destinationPicUrl;
    }
}
