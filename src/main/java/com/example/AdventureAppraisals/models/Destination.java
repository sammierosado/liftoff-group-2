package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity{

    @Lob
    private String image;
    @OneToMany(mappedBy = "destination")
    private final List<Itinerary> itinerary = new ArrayList<>();
    public Destination() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Itinerary> getItinerary() {
        return itinerary;
    }

}
