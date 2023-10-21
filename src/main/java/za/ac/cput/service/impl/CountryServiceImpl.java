package za.ac.cput.service.impl;

/* CountryServiceImpl.java
Implementation class for CountryService.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Country;
import za.ac.cput.repository.CountryRepository;
import za.ac.cput.service.CountryService;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository repo;

   @Autowired
   public CountryServiceImpl(CountryRepository repo){this.repo = repo;}

    @Override
    public Country create(Country country){return repo.save(country);}

    @Override
    public Country read (Long id){return repo.findById(id).orElse(null);}

    @Override
    public Country update(Country country){
        if (this.repo.existsById(country.getCountryID())){
            return this.repo.save(country);
        }
        return null;
    }

    @Override
    public boolean delete(Long id){
        if(this.repo.existsById(id)){
            this.repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Country> getAll(){return this.repo.findAll();}

}
