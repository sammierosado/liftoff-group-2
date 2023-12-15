package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.DestinationRepository;
import com.example.AdventureAppraisals.Data.ItineraryDetailsRepository;
import com.example.AdventureAppraisals.Data.ItineraryRepository;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private ItineraryDetailsRepository itineraryDetailsRepository;

    @Autowired
    private DestinationRepository destinationRepository;


    @GetMapping
    public String displayItineraries(Model model){
        model.addAttribute("title","All Itineraries");
        model.addAttribute("Itineraries",itineraryRepository.findAll());
        model.addAttribute("ItineraryDetails",itineraryDetailsRepository.findAll());
        model.addAttribute("Destination",destinationRepository.findAll());
        return "itinerary/index";
    }
    @GetMapping("/create")
    public String displayCreateItineraryForm(Model model){
        model.addAttribute("title","Create Itineraries");
        model.addAttribute(new Itinerary());
        model.addAttribute(new ItineraryDetails());
        model.addAttribute(new Destination());
        return "itinerary/create";
    }
    @Transactional
    @PostMapping("/create")
    public String processCreateItineraryForm(@ModelAttribute Itinerary newItinerary,@ModelAttribute ItineraryDetails newItineraryDetails,@ModelAttribute Destination newDestination, Errors errors, Model model ){
        if(errors.hasErrors()){
            model.addAttribute("title","Create itineraries");
            model.addAttribute("errorMsg","BadData");
            return "itinerary/create";
        }
        itineraryRepository.save(newItinerary);
        itineraryDetailsRepository.save(newItineraryDetails);
        destinationRepository.save(newDestination);
        return "redirect:/itineraries";
    }
    @GetMapping("/delete")
    public String displayDeleteItineraryForm(Model model){
        model.addAttribute("title","DeleteItineraries");
        model.addAttribute("Itineraries",itineraryRepository.findAll());
        model.addAttribute("ItineraryDetails",itineraryDetailsRepository.findAll());
        model.addAttribute("Destination",destinationRepository.findAll());

        return "itinerary/delete";
    }

    @PostMapping("/delete")
    public String processDeleteItinerariesForm(@RequestParam (required = false) int [] itineraryIds,@RequestParam (required = false)int [] itineraryDetailsIds,@RequestParam (required = false) int [] destinationIds, Model model){
        if(itineraryIds!= null){
            for(int id : itineraryIds){
                itineraryRepository.deleteById(id);
            }
        }
        if(itineraryDetailsIds!= null){
            for(int id : itineraryDetailsIds){
                itineraryDetailsRepository.deleteById(id);
            }
        }
        if(destinationIds!= null){
            for(int id : destinationIds){
                destinationRepository.deleteById(id);
            }
        }
        return "redirect:/itineraries";
    }





}
