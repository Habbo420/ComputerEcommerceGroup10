package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public Address create(@RequestBody Address address){return addressService.create(address);}


    @GetMapping("/read/{id}")
    public Address read(@PathVariable Long id){return addressService.read(id);}

    @PutMapping("/update/{id}")
    public Address update(@RequestBody Address address){return addressService.update(address);}

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){return addressService.delete(id);}

    @GetMapping("/getAll")
    public List<Address> getAll(){return addressService.getAll();}

}
