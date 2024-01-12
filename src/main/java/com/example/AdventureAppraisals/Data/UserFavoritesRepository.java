package com.example.AdventureAppraisals.Data;

import com.example.AdventureAppraisals.models.UserFavorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoritesRepository extends CrudRepository<UserFavorites, Integer> {
}
