package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.ItineraryDetails;
import com.example.AdventureAppraisals.models.PointsOfInterest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsOfInterestRepository extends CrudRepository<PointsOfInterest, Integer> {
}
