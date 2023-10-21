package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Enquiry;
import za.ac.cput.domain.User;
import za.ac.cput.dto.EnquiryDto;
import za.ac.cput.dto.UserDto;
import za.ac.cput.repository.EnquiryRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.EnquiryService;

import java.util.List;

/*
    EnquiryServiceImpl.java
    Author: Hanno Visser Immelman 221074414
    This is the implementation for the service EnquiryService.java
    Date: 10 - 06 - 2023
 */

@Service
public class EnquiryServiceImpl implements EnquiryService {
    private EnquiryRepository repository;
    private UserRepository userRepository;

    @Autowired
    private EnquiryServiceImpl(EnquiryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository; // Added userRepository injection
    }

    @Override
    public Enquiry create(Enquiry enquiry) {
        return this.repository.save(enquiry);
    }

    @Override
    public Enquiry read(Long customerID) {
        return this.repository.findById(customerID).orElse(null);
    }

    @Override
    public Enquiry update(Enquiry enquiry) {
        if(this.repository.existsById((enquiry.getEnquiryID())))
            return this.repository.save(enquiry);
        return null;
    }

    @Override
    public boolean delete(Long enquiryID) {
        if (this.repository.existsById(enquiryID)){
            this.repository.deleteById(enquiryID);
            return  true;
        }
        return false;
    }

    @Override
    public List<Enquiry> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Enquiry createEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = convertToEntity(enquiryDto);

        return repository.save(enquiry);
    }
    private Enquiry convertToEntity(EnquiryDto enquiryDto) {
        Enquiry enquiry = new Enquiry();
        enquiry.setEnquiryName(enquiryDto.getEnquiryName());
        enquiry.setEnquirySubjectLine(enquiryDto.getEnquirySubjectLine());
        enquiry.setEnquiryBodyContent(enquiryDto.getEnquiryBodyContent());
        enquiry.setEnquiryDate(enquiryDto.getEnquiryDate());

        UserDto customerDto = enquiryDto.getCustomer();

        User customer = userRepository.getReferenceById(customerDto.getCustomerID());

        enquiry.setCustomer(customer);

        if (customer != null) {
            enquiry.setCustomer(customer);
        } else {
            System.out.println("Could not find user!");
        }

        return enquiry;
    }

    public EnquiryDto convertToEnquiryDto(Enquiry enquiry) {
        EnquiryDto enquiryDto = new EnquiryDto();
        enquiryDto.setEnquiryName(enquiry.getEnquiryName());
        enquiryDto.setEnquirySubjectLine(enquiry.getEnquirySubjectLine());
        enquiryDto.setEnquiryBodyContent(enquiry.getEnquiryBodyContent());
        enquiryDto.setEnquiryDate(enquiry.getEnquiryDate());

        return enquiryDto;
    }

}
