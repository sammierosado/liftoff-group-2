package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;

@Entity
public class PointsOfInterest extends AbstractEntity {


    private String description;


    public PointsOfInterest(){}

    public PointsOfInterest(String description) {

        this.description = description;


    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
