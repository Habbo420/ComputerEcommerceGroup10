package za.ac.cput.service.impl;
/* CityServiceImpl.java
Implementation class for CityService.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.City;
import za.ac.cput.repository.CityRepository;
import za.ac.cput.service.CityService;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private CityRepository repository;

    @Autowired
    private CityServiceImpl(CityRepository repo){
        this.repository = repo;
    }

    @Override
    public City create(City city){return this.repository.save(city);}

    @Override
    public City read(Long cityID){return this.repository.findById(cityID).orElse(null);}

    @Override
    public City update(City city){
        if(this.repository.existsById(city.getCityID())){
            return this.repository.save(city);

        }
        return null;
    }

    @Override
    public boolean delete(Long cityID){
        if (this.repository.existsById(cityID)){
            this.repository.deleteById(cityID);
            return true;
        }
        return false;
    }

    @Override
    public List<City> getAll(){return repository.findAll();}

}
