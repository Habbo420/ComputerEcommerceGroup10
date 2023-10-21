package za.ac.cput.factory;
/*
Author: Hanno Visser Immelman 221074414
*/
import za.ac.cput.domain.User;
import za.ac.cput.domain.Enquiry;
import za.ac.cput.util.Helper;
public class EnquiryFactory {
    public static Enquiry buildEnquiry(User customer, String enquiryName, String enquirySubjectLine, String enquiryBodyContent, String enquiryDate){
        if (Helper.isNullOrEmpty(enquiryName) || Helper.isNullOrEmpty(enquirySubjectLine) || Helper.isNullOrEmpty(enquiryBodyContent) || Helper.isNullOrEmpty(enquiryDate))
            return null;

        return new Enquiry.Builder()
                .setCustomer(customer)
                .setEnquiryName(enquiryName)
                .setEnquirySubjectLine(enquirySubjectLine)
                .setEnquiryBodyContent(enquiryBodyContent)
                .setEnquiryDate(enquiryDate)
                .build();
    }
}
