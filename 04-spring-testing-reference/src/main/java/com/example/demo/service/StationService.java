package com.example.demo.service;

import com.example.demo.models.Station;
import com.example.demo.repo.StationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepo stationRepo;

    @Autowired
    ModelMapper modelMapper;

    // method to create a station
    public Station createStation(Station station) {
        return stationRepo.save(station);
    }

    public Iterable<Station> findAllStations() {
        return stationRepo.findAll();
    }

    public Station updateStation(Integer stationId, Station station) {
        Optional<Station> existingStation = stationRepo.findById(stationId);
        if (existingStation.isEmpty()) {
            throw new RuntimeException("Station with id " + stationId + " does not exist");
        }
        Station newStation = existingStation.get();
        if (station.getName() != null) {
            newStation.setName(station.getName());
        }
        if (station.getDateOpened() != null) {
            newStation.setDateOpened(station.getDateOpened());
        }
        if (station.getPlatformCount() != null) {
            newStation.setPlatformCount(station.getPlatformCount());
        }

        stationRepo.save(newStation);
        return newStation;
    }

    public Station updateStationAlt(Integer stationId, Station station) {
        Optional<Station> existingStation = stationRepo.findById(stationId);
        if (existingStation.isEmpty()) {
            throw new RuntimeException("Station with id " + stationId + " does not exist");
        }

        Station stationToUpdate = existingStation.get();
        modelMapper.map(station, stationToUpdate);

        stationRepo.save(stationToUpdate);
        return stationToUpdate;
    }

    public Station deleteStation(Integer stationId) {
        Optional<Station> existingStation = stationRepo.findById(stationId);
        if (existingStation.isEmpty()) {
            throw new RuntimeException("Station with id " + stationId + " does not exist");
        }

        // Other way to check
//        if (stationRepo.existsById(stationId))

        stationRepo.deleteById(stationId);
        return existingStation.get();
    }

    public Station findStationById(Integer stationId) {
        return stationRepo.findById(stationId).get();
    }

    public Iterable<Station> findStationByName(String name) {
        return stationRepo.findAllByNameIgnoreCase(name);
    }

    public Iterable<Station> searchStationByName(String name) {
        return stationRepo.searchAllStationsLike(name);
    }
}
