package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
