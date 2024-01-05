package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.DestinationRepository;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/destination")
public class DestinationController {
    @Autowired
    private DestinationRepository destinationRepository;


    @GetMapping
    public String displayItineraryDetails(Model model){
        model.addAttribute("title","All Itineraries");
        model.addAttribute("Destination",destinationRepository.findAll());
        return "destination/index";
    }

    @GetMapping("/create")
    public String displayCreateItineraryForm(Model model){
        model.addAttribute("title","Create Itineraries");
        model.addAttribute(new Destination());
        return "destination/create";
    }
    @PostMapping("/create")
    public String processCreateItineraryForm(@ModelAttribute Destination newDestination, Errors errors, Model model ){
        if(errors.hasErrors()){
            model.addAttribute("title","Create itineraries");
            model.addAttribute("errorMsg","BadData");
            return "destination/create";
        }
        destinationRepository.save(newDestination);
        return "redirect:/Destination";
    }
}
