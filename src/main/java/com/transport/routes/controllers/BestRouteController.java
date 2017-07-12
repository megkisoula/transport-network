package com.transport.routes.controllers;

import com.transport.routes.Domain.Station;
import com.transport.routes.Domain.TransportNetwork;
import com.transport.routes.Services.DistanceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msoula on 11/07/2017.
 */
@RestController
public class BestRouteController {

    private DistanceCalculatorService distanceCalculatorService;

    @Autowired
    public BestRouteController(DistanceCalculatorService distanceCalculatorService) {
        this.distanceCalculatorService = distanceCalculatorService;
    }

    @GetMapping(value = "/api/routes/bestroute")
    public String returnBestRouteBetweenStations(
            @RequestParam(value = "from", required = true) String start,
            @RequestParam(value = "to", required = true) String to) throws ResourceNotFoundException {


        TransportNetwork network = distanceCalculatorService.initializeStationNetwork();

        if(network.getStationList().get(start) == null){
            throw new IllegalArgumentException("There is no station with name "+ start);
        }

        if(network.getStationList().get(to) == null){
            throw new IllegalArgumentException("There is no station with name "+ to);
        }

        distanceCalculatorService.computePaths(network.getStationList().get(start));


        List<Station> path = distanceCalculatorService.getShortestPathTo(network.getStationList().get(to));

        return "The best route from " +start + "to "+ to +" is " +network.getStationList().get(to).getMinDistance() + " minutes with route "
                + path.stream().map(station -> station.getName()).collect(Collectors.toList());

    }
}