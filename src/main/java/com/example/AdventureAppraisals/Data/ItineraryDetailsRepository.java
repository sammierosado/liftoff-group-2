package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.ItineraryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryDetailsRepository extends CrudRepository<ItineraryDetails, Integer> {
}
