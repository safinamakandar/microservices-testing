package com.example.demo.controllers;

import com.example.demo.models.Station;
import com.example.demo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;


    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }


    @GetMapping("/stations")
    public Iterable<Station> getStations() {
        return stationService.findAllStations();
    }

    @GetMapping("/station/{stationId}")
    public Station getStationById(@PathVariable Integer stationId) {
        return stationService.findStationById(stationId);
    }

    @GetMapping("/station")
    public Iterable<Station> getStationByName(@RequestParam(value = "name") String name) {
        return stationService.findStationByName(name);
    }

    @GetMapping("/stations/search")
    public Iterable<Station> searchStations(@RequestParam(value = "name") String name) {
        return  stationService.searchStationByName(name);
    }

    @PostMapping("/station")
    public Station postStation(@RequestBody Station station) {
        return stationService.createStation(station);
    }

    @PutMapping("/station/{stationId}")
    public Station putStation(@PathVariable Integer stationId, @RequestBody Station station) {
        return stationService.updateStationAlt(stationId, station);
    }

    @DeleteMapping("/station/{stationId}")
    public Station deleteStation(@PathVariable Integer stationId) {
        return stationService.deleteStation(stationId);
    }

}
