package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.Data.ItineraryRepository;
import com.example.AdventureAppraisals.Data.ReviewRepository;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @GetMapping("/reviews/{id}")
    public List<Review> getReviews(@PathVariable int id) {
        return null;
    }

    @CrossOrigin
    @PostMapping("/create/{id}")
    @ResponseBody
    public Review createReview(@RequestBody Review reviewObject, @PathVariable int id) {

        Itinerary itinerary;

        if (itineraryRepository.findById(id).isPresent()) {
            itinerary = itineraryRepository.findById(id).get();
        } else {
            itinerary = null;
        }

        Review review = new Review(reviewObject.getName(), itinerary, reviewObject.getUserEmail(), reviewObject.getReview(), reviewObject.getRating());

        reviewRepository.save(review);

        return review;
    }
}