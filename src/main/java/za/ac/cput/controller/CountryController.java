package za.ac.cput.controller;

/* CountryController.java
Controller class for Country.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Country;
import za.ac.cput.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/create")
    public Country create(@RequestBody Country country){return countryService.create(country);}

    @GetMapping("/read/{id}")
    public Country read(@PathVariable Long id){return countryService.read(id);}

    @PostMapping("/update")
    public Country update(@RequestBody Country country){return countryService.update(country);}

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){return countryService.delete(id);}

    @GetMapping("/getAll")
    public List<Country> getAll(){return countryService.getAll();}


}
