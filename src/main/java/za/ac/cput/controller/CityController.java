package za.ac.cput.controller;
/* CityController.java
Controller for City
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.City;
import za.ac.cput.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/create")
    public City create(@RequestBody City city){return cityService.create(city);}

    @GetMapping("/read/{id}")
    public City read(@PathVariable Long id){return cityService.read(id);}

    @PostMapping("/update")
    public City update(@RequestBody City city){return cityService.update(city);}

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){return cityService.delete(id);}

    @GetMapping("/getAll")
    public List<City> getAll(){return cityService.getAll();}

}
