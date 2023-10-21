package za.ac.cput.service.impl;

/* SalesServiceImpl.java
Service implementation for the Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Enquiry;
import za.ac.cput.domain.Sales;
import za.ac.cput.domain.User;
import za.ac.cput.dto.EnquiryDto;
import za.ac.cput.dto.SalesDto;
import za.ac.cput.dto.UserDto;
import za.ac.cput.repository.SalesRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.SalesService;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository repository;

    private UserRepository userRepository;


    @Autowired
    private SalesServiceImpl(SalesRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }
    @Override
    public Sales create(Sales sales) {
        return this.repository.save(sales);
    }

    @Override
    public Sales read(Long salesID) {
        return this.repository.findById(salesID).orElse(null);
    }

    @Override
    public Sales update(Sales sales) {
        if(this.repository.existsById((sales.getSaleID())))
            return this.repository.save(sales);
        return null;
    }

    @Override
    public boolean delete(Long salesID) {
        if(this.repository.existsById(salesID)){
            this.repository.deleteById(salesID);
            return true;
        }
        return false;
    }

    @Override
    public List<Sales> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Sales createSale(SalesDto salesDto) {
        Sales sales = convertToEntity(salesDto);

        return repository.save(sales);
    }

    public Sales convertToEntity(SalesDto salesDto) {
        Sales sales = new Sales();
        sales.setSaleID(salesDto.getSaleID());
        sales.setSaleDate(salesDto.getSaleDate());
        sales.setTotalAmount(salesDto.getTotalAmount());

        UserDto customerDto = salesDto.getCustomer();
        User customer = userRepository.getReferenceById(customerDto.getCustomerID());

        sales.setCustomer(customer);



        if (customer != null) {
            sales.setCustomer(customer);
        } else {
            System.out.println("Could not find user!");
        }

        return sales;
    }

    @Override
    public SalesDto convertToSalesDto(Sales sales) {

        SalesDto salesDto = new SalesDto();
        salesDto.setSaleID(sales.getSaleID());
        salesDto.setSaleDate(sales.getSaleDate());
        salesDto.setTotalAmount(sales.getTotalAmount());

        return salesDto;
    }
}
