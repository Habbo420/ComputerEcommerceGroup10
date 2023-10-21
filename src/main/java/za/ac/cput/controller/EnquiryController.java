package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Enquiry;
import za.ac.cput.dto.EnquiryDto;
import za.ac.cput.service.EnquiryService;

import java.util.List;

@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    /*@PostMapping("/create")
    public Enquiry create (@RequestBody Enquiry enquiry){
        return enquiryService.create(enquiry);
    }*/

    @PostMapping("/create")
    public ResponseEntity<EnquiryDto> createEnquiry(@RequestBody EnquiryDto enquiryDto) {
        Enquiry createdEnquiry = enquiryService.createEnquiry(enquiryDto);
        EnquiryDto responseDto = enquiryService.convertToEnquiryDto(createdEnquiry);

        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public Enquiry read(@PathVariable Long id){
        return enquiryService.read(id);
    }

    @PutMapping("/update")
    public Enquiry update(@RequestBody Enquiry enquiry){
        return enquiryService.update(enquiry);
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return enquiryService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Enquiry> getAll(){
        return enquiryService.getAll();
    }
}
