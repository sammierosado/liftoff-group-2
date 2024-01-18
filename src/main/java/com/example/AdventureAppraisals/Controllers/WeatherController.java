package com.example.AdventureAppraisals.Controllers;
import com.example.AdventureAppraisals.models.Destination;
import com.example.AdventureAppraisals.models.Itinerary;
import com.example.AdventureAppraisals.Data.ItineraryRepository;
import com.example.AdventureAppraisals.Data.DestinationRepository;
import com.example.AdventureAppraisals.models.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

        @RestController
        @RequestMapping("/weather")
        @CrossOrigin
        public class WeatherController {
            private final String apiKey = "cd98ff688924fb4a30ea8344860d0639";
            private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

            @Autowired
            private ItineraryRepository itineraryRepository;

            @GetMapping("/itinerary/{id}")
            public ResponseEntity<Map<String, Object>> getWeather(@PathVariable int id) {
                Itinerary itinerary = itineraryRepository.findById(id).orElse(null);

                if (itinerary == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Itinerary not found"));
                }

                String itineraryName = itinerary.getName();
                String[] parts = itineraryName.split(",");
                String city = parts[1];

                String weatherUrl = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=imperial";

                RestTemplate restTemplate = new RestTemplate();
                String weatherData = restTemplate.getForObject(weatherUrl, String.class);

                Map<String, Object> response = new HashMap<>();
                response.put("weatherData", weatherData);
                response.put("itineraryName", itineraryName);
                return ResponseEntity.ok(response);
            }
        }
