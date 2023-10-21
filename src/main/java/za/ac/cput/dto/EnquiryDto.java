package za.ac.cput.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnquiryDto implements Serializable {
       Long enquiryID;
       String enquiryName;
       String enquirySubjectLine;
       String enquiryBodyContent;
       String enquiryDate;
       UserDto customer;
}