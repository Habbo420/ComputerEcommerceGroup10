package za.ac.cput.service;

import za.ac.cput.domain.Enquiry;
import za.ac.cput.dto.EnquiryDto;

import java.util.List;
import java.util.Set;

/*
    EnquiryService.java
    Author: Hanno Visser Immelman 221074414
    This is the Service for the service the Enquiry entity
    Date: 10 - 06 - 2023
 */

public interface EnquiryService extends IService<Enquiry, Long>{
    List<Enquiry> getAll();

    Enquiry createEnquiry(EnquiryDto enquiryDto);

    EnquiryDto convertToEnquiryDto(Enquiry enquiry);
}
