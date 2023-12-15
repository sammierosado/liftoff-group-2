package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.ItineraryDetailsRepository;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itineraryDetails")
public class ItineraryDetailsController {

    @Autowired
    private ItineraryDetailsRepository itineraryDetailsRepository;

    @GetMapping
    public String displayDestination(Model model){
        model.addAttribute("title","All Itineraries");

        model.addAttribute("ItineraryDetails",itineraryDetailsRepository.findAll());

        return "itineraryDetails/index";
    }
    @GetMapping("/create")
    public String displayCreateItineraryForm(Model model){
        model.addAttribute("title","Create Itineraries");
        model.addAttribute(new ItineraryDetails());
        return "itineraryDetails/create";
    }
    @PostMapping("/create")
    public String processCreateItineraryForm(@ModelAttribute ItineraryDetails newItineraryDetails, Errors errors, Model model ){
        if(errors.hasErrors()){
            model.addAttribute("title","Create itineraries");
            model.addAttribute("errorMsg","BadData");
            return "itineraryDetails/create";
        }
        itineraryDetailsRepository.save(newItineraryDetails);
        return "redirect:/itineraryDetails";
    }
}
