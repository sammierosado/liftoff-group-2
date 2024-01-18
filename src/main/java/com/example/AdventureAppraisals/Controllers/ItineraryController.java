package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.*;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public String displayItineraries(Model model) {
        model.addAttribute("All Itineraries", "All Itineraries");
        model.addAttribute("ItinerariesDetails", itineraryRepository.findAll());
        model.addAttribute("ItineraryDetails", itineraryDetailsRepository.findAll());
        model.addAttribute("Destination", destinationRepository.findAll());
        return "itinerary/index";
    }

    @GetMapping("/destination")
    public String displayDestination(Model model){
        model.addAttribute("Destination", destinationRepository.findAll());
        return "itinerary/destination";
    }

    @GetMapping("/create")
    public String displayCreateItineraryForm(Model model) {
        model.addAttribute("Create Itineraries", "Create Itineraries");
        model.addAttribute(new Itinerary());
        model.addAttribute(new ItineraryDetails());
        model.addAttribute(new Destination());
        return "itinerary/create";
    }

    @Transactional
    @PostMapping("/create")
    public String processCreateItineraryForm(@ModelAttribute Itinerary newItinerary, @ModelAttribute ItineraryDetails newItineraryDetails, @ModelAttribute Destination newDestination, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("Create Itineraries", "Create itineraries");
            model.addAttribute("errorMsg", "BadData");
            return "itinerary/create";
        }
        itineraryRepository.save(newItinerary);
        itineraryDetailsRepository.save(newItineraryDetails);
        destinationRepository.save(newDestination);
        return "redirect:/itineraries";
    }


    @GetMapping("/delete/{id}")
    public String displayDeleteItineraryForm1(Model model,@PathVariable int id) {
        model.addAttribute("Delete Itineraries", "Delete Itineraries");
        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        model.addAttribute("itineraryDetails", itineraryDetails);

        return "itinerary/delete";
    }


    @PostMapping("/delete/{id}")
    public String processDeleteItinerariesForm(@PathVariable int id){ //@RequestParam(required = false) int[] destinationIds, Model model) {

        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        String priorName = itineraryDetails.getName();
        Iterable<Itinerary> itineraryIterable = itineraryRepository.findAll();
        for(Itinerary itinerary : itineraryIterable){
            if((itinerary.getName().toLowerCase().contains(priorName.toLowerCase()))){
                itineraryRepository.delete(itinerary);
            }
        }

        Iterable<Destination> destinationIterable = destinationRepository.findAll();
        for(Destination destination : destinationIterable){
            if((destination.getName().toLowerCase().contains(priorName.toLowerCase()))){
                destinationRepository.delete(destination);
            }
        }
        itineraryDetailsRepository.deleteById(id);
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


    @Transactional
    @PatchMapping("/updateDetails/{id}")
    public String updateItineraryFormData8(Model model, @PathVariable int id, @RequestParam String name, @RequestParam String fromCity, @RequestParam String toCity, @RequestParam LocalDateTime travelStartDateTime, @RequestParam LocalDateTime travelEndDateTime) {
        model.addAttribute("Update itineraries", "Update Itineraries");
        Optional<ItineraryDetails> itineraryDetailsOptional = itineraryDetailsRepository.findById(id);
        ItineraryDetails itineraryDetails = itineraryDetailsOptional.get();
        String priorName = itineraryDetails.getName();
        itineraryDetails.setName(name);
        itineraryDetails.setFromCity(fromCity);
        itineraryDetails.setToCity(toCity);
        itineraryDetails.setTravelEndDateTime(travelStartDateTime);
        itineraryDetails.setTravelEndDateTime(travelEndDateTime);
        itineraryDetailsRepository.save(itineraryDetails);

        Iterable<Itinerary> itineraryIterable = itineraryRepository.findAll();
        for(Itinerary itinerary : itineraryIterable){
            if((itinerary.getName().contains(priorName))){
                itinerary.setName(name);
                itineraryRepository.save(itinerary);
            }
        }
        Iterable<Destination> destinationIterable = destinationRepository.findAll();
        for(Destination destination : destinationIterable){
            if((destination.getName().contains(priorName))){
                destination.setName(name);
                destinationRepository.save(destination);
            }
        }

        return "redirect:/itineraries";
    }


    @GetMapping("/search")
    public String searchFormDisplay(Model model) {
        model.addAttribute("Search", "Search");
        return "itinerary/searchForm";
    }


    @PostMapping("/search/")
    public String processSearchForm(Model model, @RequestParam String word) {
        model.addAttribute("Search Results", "Search Results");
        Iterable<ItineraryDetails> itineraryDetailsIterable = itineraryDetailsRepository.findAll();
        List<ItineraryDetails> matchingItineraryDetails = new ArrayList<>();
        for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {

            if (itineraryDetails.getName().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingItineraryDetails.contains(itineraryDetails)) {
                    matchingItineraryDetails.add(itineraryDetails);
                }
            }
            if (itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingItineraryDetails.contains(itineraryDetails)) {
                    matchingItineraryDetails.add(itineraryDetails);
                }
            }
            if (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingItineraryDetails.contains(itineraryDetails)) {
                    matchingItineraryDetails.add(itineraryDetails);
                }
            }
            if (itineraryDetails.getTravelStartDateTime().toString().toLowerCase().equals(word.toLowerCase())) {
                if (!matchingItineraryDetails.contains(itineraryDetails)) {
                    matchingItineraryDetails.add(itineraryDetails);
                }
            }
            if (itineraryDetails.getTravelEndDateTime().toString().toLowerCase().equals(word.toLowerCase())) {
                if (!matchingItineraryDetails.contains(itineraryDetails)) {
                    matchingItineraryDetails.add(itineraryDetails);
                }
            }
        }


        if(matchingItineraryDetails.isEmpty()){
            return "itinerary/NoSearchResults";
        }
        else{
            model.addAttribute("itineraryDetails", matchingItineraryDetails);
            return "itinerary/searchDisplay";
        }

    }

    @GetMapping("/upload/{id}")
    public String uploadImagePage(@PathVariable int id, Model model) {
        model.addAttribute("Upload Image","Upload Image");
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        model.addAttribute("Destination", destination);
        return "itinerary/uploadForm";
    }

    @PutMapping("/upload/{id}")
    public String processUploadImageForm(Model model, @RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        model.addAttribute("Upload Image","Upload Image");
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(File.separator+"Users"+File.separator+"cameron.freeman"+File.separator +"Documents"+File.separator +"liftoff-group-2-Adventure"+File.separator +"src"+File.separator +"main"+File.separator+"resources"+File.separator+"static"+File.separator+"image"+File.separator + file.getOriginalFilename());
        Files.write(path,bytes);
        destination.setImage(File.separator+"image"+File.separator + file.getOriginalFilename());
        model.addAttribute("Destination", destination);
        destinationRepository.save(destination);
        return "redirect:/itineraries";

    }

    @CrossOrigin
    @GetMapping("/itinerary/{id}")
    @ResponseBody
    public Optional<Itinerary> getItineraryById(@PathVariable int id) {
        System.out.println(id);
        System.out.println(itineraryRepository.findById(id));
        return itineraryRepository.findById(id);
    }

}