package es.uma.ingweb.coffeecar.controller;


import es.uma.ingweb.coffeecar.entities.StopHierarchy;
import es.uma.ingweb.coffeecar.entities.GeographicalCoordinates;
import es.uma.ingweb.coffeecar.entities.User;
import es.uma.ingweb.coffeecar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stops")
public class StopConsumer {

    private final RestTemplate restTemplate;

    public StopConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<StopHierarchy.StopInfoResponse.StopData> getBus() {
        String uri = "http://datosabiertos.malaga.eu/api/3/action/datastore_search?resource_id=d7eb3174-dcfb-4917-9876-c0e21dd810e3";

        return Objects.requireNonNull(restTemplate.getForObject(uri, StopHierarchy.class)).getResult().getStopsData();
    }

    @GetMapping(value = "/search/findNearby")
    public List<StopHierarchy.StopInfoResponse.StopData> getBusByPosition(@RequestParam(name = "lat") float lat,
                                                                          @RequestParam(name = "lon") float lon) {
        String uri = "http://datosabiertos.malaga.eu/api/3/action/datastore_search?resource_id=d7eb3174-dcfb-4917-9876-c0e21dd810e3";
        List<StopHierarchy.StopInfoResponse.StopData> stops = restTemplate.getForObject(uri, StopHierarchy.class)
              .getResult()
              .getStopsData();
        GeographicalCoordinates requestCoordinates = new GeographicalCoordinates(lon, lat);

        List<StopHierarchy.StopInfoResponse.StopData> filteredStops = stops.stream().filter(stop -> {
            GeographicalCoordinates stopCoordinates = new GeographicalCoordinates(stop.getLon(), stop.getLat());
            return requestCoordinates.distanceTo(stopCoordinates) <= 1000;
        }).collect(Collectors.toList());
        return filteredStops;
    }
}
