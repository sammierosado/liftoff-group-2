package com.example.AdventureAppraisals.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Review extends AbstractEntity{

    public String userEmail;

    public String review;

    public int rating;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    public Itinerary itinerary;

    public Review() {}

    public Review(String name, Itinerary itinerary, String userEmail, String review, int rating) {
        this.setName(name);
        this.itinerary = itinerary;
        this.userEmail = userEmail;
        this.review = review;
        this.rating = rating;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }
}