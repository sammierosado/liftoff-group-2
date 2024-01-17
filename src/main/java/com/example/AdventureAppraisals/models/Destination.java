package com.example.AdventureAppraisals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity{

    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Itinerary> getItinerary() {
        return itinerary;
    }

}
