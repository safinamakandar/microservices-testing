package com.example.demo.repo;

import com.example.demo.models.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Our interface here extends CrudRepository which is an interface provided by spring data
// It will give us access to all the methods that CrudRepository
// Station is the type of model/entity, integer is the ID type.
public interface StationRepo extends CrudRepository<Station, Integer> {

    List<Station> findAllByNameIgnoreCase(String name);

    @Query("SELECT s FROM Station s WHERE s.name LIKE %:name%")
    List<Station> searchAllStationsLike(String name);

}
