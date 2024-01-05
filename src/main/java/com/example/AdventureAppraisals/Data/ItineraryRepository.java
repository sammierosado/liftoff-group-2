package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary, Integer> {

}
