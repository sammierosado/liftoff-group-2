package com.example.AdventureAppraisals.Controllers;

import com.example.AdventureAppraisals.Data.*;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.models.ItineraryDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
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
        model.addAttribute("Itineraries", itineraryRepository.findAll());
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

    @GetMapping("/delete")
    public String displayDeleteItineraryForm(Model model) {
        model.addAttribute("Delete Itineraries", "Delete Itineraries");
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
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        destination.setName(name);
        destinationRepository.save(destination);
        /*itineraryRepository.save(itinerary1);*/
        return "redirect:/itineraries";
    }


    @GetMapping("/search")
    public String searchFormDisplay(Model model) {
        model.addAttribute("Search", "Search");
        return "itinerary/searchForm";
    }

    @PostMapping("/search")
    public String searchFormDisplay3(Model model) {
        model.addAttribute("Search", "Search");
        return "itinerary/searchDisplay";
    }

    @GetMapping("/search/")
    public String searchFormDisplay1(Model model, @RequestParam String word) {
        model.addAttribute("Search", "Search");
        Iterable<Itinerary> itineraryIterable = itineraryRepository.findAll();
        Iterable<ItineraryDetails> itineraryDetailsIterable = itineraryDetailsRepository.findAll();
        Iterable<Destination> destinationIterable = destinationRepository.findAll();
        List<Itinerary> matchingItinerary = new ArrayList<>();
        List<Destination> matchingDestination = new ArrayList<>();
        for (Itinerary itinerary : itineraryIterable) {
            if (itinerary.getName().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingItinerary.contains(itinerary)) {
                    matchingItinerary.add(itinerary);
                    break;
                }
                break;
            }

        }
        if (matchingItinerary.isEmpty()) {
            for (Itinerary itinerary : itineraryIterable) {

                for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
                    if (!(itinerary.getName().toLowerCase().contains(word.toLowerCase()))) {
                        if ((itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) || (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase()))) {
                            if (!matchingItinerary.contains(itinerary)) {
                                matchingItinerary.add(itinerary);
                                break;
                            }
                            break;
                        }
                    }

                }
                continue;
            }
        }
        model.addAttribute("itinerary", matchingItinerary);


        List<ItineraryDetails> matchingItineraryDetails = new ArrayList<>();
        for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
            if (itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getTravelStartDateTime().equals(word)) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getTravelEndDateTime().equals(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
        }
        model.addAttribute("itineraryDetails", matchingItineraryDetails);
        for (Destination destination : destinationIterable) {
            if (destination.getName().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingDestination.contains(destination)) {
                    matchingDestination.add(destination);
                    break;
                }
                break;
            }

        }
        if (matchingDestination.isEmpty()) {
            for (Destination destination : destinationIterable) {
                for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
                    if (!(destination.getName().toLowerCase().contains(word.toLowerCase()))) {
                        if ((itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) || (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase()))) {
                            if (!matchingDestination.contains(destination)) {
                                matchingDestination.add(destination);
                                break;
                            }
                            break;
                        }
                    }

                }
                continue;
            }
        }
        model.addAttribute("destination", matchingDestination);

        return "itinerary/searchDisplay";
    }

    @PostMapping("/search/")
    public String processSearchForm(Model model, @RequestParam String word) {
        model.addAttribute("Search Results", "Search Results");
        Iterable<Itinerary> itineraryIterable = itineraryRepository.findAll();
        Iterable<ItineraryDetails> itineraryDetailsIterable = itineraryDetailsRepository.findAll();
        Iterable<Destination> destinationIterable = destinationRepository.findAll();
        List<Itinerary> matchingItinerary = new ArrayList<>();
        List<ItineraryDetails> matchingItineraryDetails = new ArrayList<>();
        List<Destination> matchingDestination = new ArrayList<>();
        for (Itinerary itinerary : itineraryIterable) {
            if (itinerary.getName().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingItinerary.contains(itinerary)) {
                    matchingItinerary.add(itinerary);
                    break;
                }
                break;
            }

        }
        if (matchingItinerary.isEmpty()) {
            for (Itinerary itinerary : itineraryIterable) {
                for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
                    if (!(itinerary.getName().toLowerCase().contains(word.toLowerCase()))) {
                        if ((itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) || (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase()))) {
                            if (!matchingItinerary.contains(itinerary)) {
                                matchingItinerary.add(itinerary);
                                break;
                            }
                            break;
                        }
                    }

                }
                continue;
            }
        }
        model.addAttribute("itinerary", matchingItinerary);

        for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
            if (itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getTravelStartDateTime().toString().toLowerCase().equals(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
            if (itineraryDetails.getTravelEndDateTime().toString().toLowerCase().equals(word.toLowerCase())) {
                matchingItineraryDetails.add(itineraryDetails);
            }
        }
        model.addAttribute("itineraryDetails", matchingItineraryDetails);
        for (Destination destination : destinationIterable) {
            if (destination.getName().toLowerCase().contains(word.toLowerCase())) {
                if (!matchingDestination.contains(destination)) {
                    matchingDestination.add(destination);
                    break;
                }
                break;
            }

        }
        if (matchingDestination.isEmpty()) {
            for (Destination destination : destinationIterable) {

                for (ItineraryDetails itineraryDetails : itineraryDetailsIterable) {
                    if (!(destination.getName().toLowerCase().contains(word.toLowerCase()))) {
                        if ((itineraryDetails.getFromCity().toLowerCase().contains(word.toLowerCase())) || (itineraryDetails.getToCity().toLowerCase().contains(word.toLowerCase()))) {
                            if (!matchingDestination.contains(destination)) {
                                matchingDestination.add(destination);
                                break;
                            }
                            break;
                        }

                    }

                }
                continue;
            }
        }
        model.addAttribute("destination", matchingDestination);
        return "itinerary/searchDisplay";
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
        //var base64EncodedImage = Base64.getEncoder().encodeToString(file.toString().getBytes());
        model.addAttribute("Upload Image","Upload Image");
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        Destination destination = destinationOptional.get();
        //ImageIcon myIcon = new ImageIcon(getClass().getClassLoader().getResource(file.getPath()));
        //destination.setImage(myIcon);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(File.separator+"Users"+File.separator+"sugan"+File.separator +"IdeaProjects"+File.separator +"liftoff-group-2"+File.separator +"src"+File.separator +"main"+File.separator+"resources"+File.separator+"static"+File.separator+"image"+File.separator + file.getOriginalFilename());
        Files.write(path,bytes);
        destination.setImage(File.separator+"image"+File.separator + file.getOriginalFilename());
        model.addAttribute("Destination", destination);
        destinationRepository.save(destination);

        //model.addAttribute("base64EncodedImage",base64EncodedImage);
        return "redirect:/itineraries";

    }

    @CrossOrigin
    @GetMapping("/itinerary/{id}")
    @ResponseBody
    public Optional<Itinerary> getItineraryById(@PathVariable int id) {
        return itineraryRepository.findById(id);
    }

}