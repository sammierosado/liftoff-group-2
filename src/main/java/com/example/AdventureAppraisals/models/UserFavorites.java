package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserFavorites extends AbstractEntity {

    public List<Integer> favoritedItineraryList = new ArrayList<>();

    public UserFavorites() {}

    public UserFavorites(String name, List<Integer> itineraryList) {
        this.setName(name);
        this.favoritedItineraryList = itineraryList;
    }

    public List<Integer> getFavoritedItineraryList() {
        return favoritedItineraryList;
    }

    public void setFavoritedItineraryList(List<Integer> favoritedItineraryList) {
        this.favoritedItineraryList = favoritedItineraryList;
    }
}
