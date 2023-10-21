package za.ac.cput.service;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Sales;

import java.util.List;
import java.util.Set;

public interface AddressService extends IService <Address, Long> {
    List<Address> getAll();
}
