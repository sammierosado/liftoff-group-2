package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.DestinationRepository;
import com.example.AdventureAppraisals.Data.ItineraryDetailsRepository;
import com.example.AdventureAppraisals.Data.ItineraryRepository;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import jakarta.transaction.Transactional;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public String displayItineraries(Model model) {
        model.addAttribute("title", "All Itineraries");
        model.addAttribute("Itineraries", itineraryRepository.findAll());
        model.addAttribute("ItineraryDetails", itineraryDetailsRepository.findAll());
        model.addAttribute("Destination", destinationRepository.findAll());
        return "itinerary/index";
    }

    @GetMapping("/create")
    public String displayCreateItineraryForm(Model model) {
        model.addAttribute("title", "Create Itineraries");
        model.addAttribute(new Itinerary());
        model.addAttribute(new ItineraryDetails());
        model.addAttribute(new Destination());
        return "itinerary/create";
    }

    @Transactional
    @PostMapping("/create")
    public String processCreateItineraryForm(@ModelAttribute Itinerary newItinerary, @ModelAttribute ItineraryDetails newItineraryDetails, @ModelAttribute Destination newDestination, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create itineraries");
            model.addAttribute("errorMsg", "BadData");
            return "itinerary/create";
        }
        itineraryRepository.save(newItinerary);
        itineraryDetailsRepository.save(newItineraryDetails);
        destinationRepository.save(newDestination);
        return "redirect:/itineraries";
    }

    @GetMapping("/delete")
    public String displayDeleteItineraryForm(Model model) {
        model.addAttribute("title", "DeleteItineraries");
        model.addAttribute("Itineraries", itineraryRepository.findAll());
        model.addAttribute("ItineraryDetails", itineraryDetailsRepository.findAll());
        model.addAttribute("Destination", destinationRepository.findAll());

        return "itinerary/delete";
    }

    @PostMapping("/delete")
    public String processDeleteItinerariesForm(@RequestParam(required = false) int[] itineraryIds, @RequestParam(required = false) int[] itineraryDetailsIds, @RequestParam(required = false) int[] destinationIds, Model model) {
        if (itineraryIds != null) {
            for (int id : itineraryIds) {
                itineraryRepository.deleteById(id);
            }
        }
        if (itineraryDetailsIds != null) {
            for (int id : itineraryDetailsIds) {
                itineraryDetailsRepository.deleteById(id);
            }
        }
        if (destinationIds != null) {
            for (int id : destinationIds) {
                destinationRepository.deleteById(id);
            }
        }
        return "redirect:/itineraries";
    }


    @Transactional
    @GetMapping("/update/{id}")
    public String updateItineraryFormData1(
            Model model, @PathVariable("id") int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Itinerary> itineraryOptional = itineraryRepository.findById(id);
        Itinerary itinerary = itineraryOptional.get();
        model.addAttribute("itinerary", itinerary);
        return "itinerary/update";
    }

    @GetMapping("/update")
    public String updateItineraryFormDataDisplay2(
            Model model, @RequestParam int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Itinerary> itineraryOptional = itineraryRepository.findById(id);
        Itinerary itinerary = itineraryOptional.get();
        model.addAttribute("itinerary", itinerary);
        return "itinerary/update";
    }


    @Transactional
    @PutMapping("/update")
    public String updateItineraryFormDataDisplay3(
            Model model, @ModelAttribute Itinerary itinerary) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        itineraryRepository.save(itinerary);

        return "redirect:/itineraries";
    }

    @Transactional
    @PutMapping("/update/{id}")
    public String updateItineraryFormData4(Model model, @PathVariable int id, @RequestParam String name) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Itinerary> itineraryOptional = itineraryRepository.findById(id);
        Itinerary itinerary = itineraryOptional.get();
        itinerary.setName(name);
        itineraryRepository.save(itinerary);
        /*itineraryRepository.save(itinerary1);*/
        return "redirect:/itineraries";
    }



    @Transactional
    @GetMapping("/updateDetails/{id}")
    public String updateItineraryFormData5(
            Model model, @PathVariable("id") int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        model.addAttribute("itineraryDetails", itineraryDetails);
        return "itinerary/updateDetails";
    }

    @GetMapping("/updateDetails")
    public String updateItineraryFormDataDisplay6(
            Model model, @RequestParam int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        model.addAttribute("itineraryDetails", itineraryDetails);
        return "itinerary/updateDetails";
    }


    @Transactional
    @PatchMapping("/updateDetails")
    public String updateItineraryFormDataDisplay7(
            Model model, @ModelAttribute ItineraryDetails itineraryDetails) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        itineraryDetailsRepository.save(itineraryDetails);

        return "redirect:/itineraries";
    }

    @Transactional
    @PatchMapping("/updateDetails/{id}")
    public String updateItineraryFormData8(Model model, @PathVariable int id, @RequestParam String fromCity, @RequestParam String toCity, @RequestParam LocalDateTime travelStartDateTime, @RequestParam LocalDateTime travelEndDateTime) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        itineraryDetails.setFromCity(fromCity);
        itineraryDetails.setToCity(toCity);
        itineraryDetails.setTravelEndDateTime(travelStartDateTime);
        itineraryDetails.setTravelEndDateTime(travelEndDateTime);
        itineraryDetailsRepository.save(itineraryDetails);
        /*itineraryRepository.save(itinerary1);*/
        return "redirect:/itineraries";
    }


    @Transactional
    @GetMapping("/updateDestination/{id}")
    public String updateItineraryFormData9(
            Model model, @PathVariable("id") int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        model.addAttribute("destination", destination);
        return "itinerary/updateDestination";
    }

    @GetMapping("/updateDestination")
    public String updateItineraryFormDataDisplay10(
            Model model, @RequestParam int id) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        model.addAttribute("destination", destination);
        return "itinerary/updateDestination";
    }


    @Transactional
    @PatchMapping("/updateDestination")
    public String updateItineraryFormDataDisplay11(
            Model model, @ModelAttribute Destination destination) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        destinationRepository.save(destination);

        return "redirect:/itineraries";
    }

    @Transactional
    @PutMapping("/updateDestination/{id}")
    public String updateItineraryFormData12(Model model, @PathVariable int id, @RequestParam String name) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<Destination>  destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        destination.setName(name);
        destinationRepository.save(destination);
        /*itineraryRepository.save(itinerary1);*/
        return "redirect:/itineraries";
    }

}

