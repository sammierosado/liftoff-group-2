package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.ReviewRepository;
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

    @GetMapping("/reviews/{id}")
    public List<Review> getReviews(@PathVariable int id) {
        return null;
    }

    @CrossOrigin
    @PostMapping("/create")
    @ResponseBody
    public Review createReview(@RequestBody Review review) {

        reviewRepository.save(review);

        return review;
    }
}