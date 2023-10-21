package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.service.AddressService;

import java.util.List;
import java.util.Set;
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    @Autowired
    public AddressServiceImpl(AddressRepository repository){
        this.repository = repository;
    }

    @Override
    public Address create(Address address) {
        return this.repository.save(address);
    }

    @Override
    public Address read(Long addressID) {
        return this.repository.findById(addressID).orElse(null);
    }

    @Override
    public Address update(Address address) {
        if (this.repository.existsById(address.getAddressID()))
            return this.repository.save(address);
        return null;
    }

    @Override
    public boolean delete(Long addressID) {
        if(this.repository.existsById(addressID)){
            this.repository.deleteById(addressID);
            return true;
        }
        return false;
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }

}
