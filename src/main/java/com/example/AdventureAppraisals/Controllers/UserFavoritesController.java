package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.ItineraryRepository;
import com.example.AdventureAppraisals.Data.UserFavoritesRepository;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.UserFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/userfavorites")
public class UserFavoritesController {

    @Autowired
    private UserFavoritesRepository userFavoritesRepository;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @GetMapping("/{name}")
    public List<Integer> getUsersFavoritesList(@PathVariable String name) {

        Iterable<UserFavorites> userFavoritesList = userFavoritesRepository.findAll();

        for (UserFavorites userFavorite : userFavoritesList) {
            if (userFavorite.getName().equals(name)) {
                return userFavorite.getFavoritedItineraryList();
            }
        }

        return null;
    }

    @CrossOrigin
    @PostMapping("/add/{name}")
    public UserFavorites addToUsersFavoritesList(@RequestBody Itinerary itinerary, @PathVariable String name) {

        List<Integer> newUserFavoritesList = new ArrayList<>();
        newUserFavoritesList.add(itinerary.getId());
        UserFavorites newUserFavorites = new UserFavorites(name, newUserFavoritesList);

        Iterable<UserFavorites> userFavoritesList = userFavoritesRepository.findAll();

        for (UserFavorites userFavorite : userFavoritesList) {
            if (userFavorite.getName().equals(name)) {
                newUserFavoritesList = userFavorite.getFavoritedItineraryList();
                newUserFavoritesList.add(itinerary.getId());
                userFavorite.setFavoritedItineraryList(newUserFavoritesList);
                return userFavoritesRepository.save(userFavorite);
            }
        }

        return userFavoritesRepository.save(newUserFavorites);
    }
}
