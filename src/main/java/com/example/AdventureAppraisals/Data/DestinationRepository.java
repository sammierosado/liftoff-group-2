package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Integer> {
}
