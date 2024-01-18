package com.example.AdventureAppraisals.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination extends AbstractEntity{

//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
//    private byte[] image;

    private String image;


    @OneToMany(mappedBy = "destination")
    private final List<Itinerary> itinerary = new ArrayList<>();
    public Destination() {}

    public Destination(String image) {
        this.image = image;
    }



    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Itinerary> getItinerary() {
        return itinerary;
    }


    }


