package za.ac.cput.domain;
/*
Author: Hanno Visser Immelman 221074414
*/
import jakarta.persistence.*;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Setter
public class Enquiry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    private Long enquiryID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID")
    private User customer;

    private String enquiryName;
    private String enquirySubjectLine;
    @Column(columnDefinition = "LONGTEXT")
    private String enquiryBodyContent;
    private String enquiryDate;

    public Enquiry() {
    }

    public Long getEnquiryID() {
        return enquiryID;
    }

    public String getEnquiryName() {
        return enquiryName;
    }

    public String getEnquirySubjectLine() {
        return enquirySubjectLine;
    }

    public String getEnquiryBodyContent() {
        return enquiryBodyContent;
    }

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public User getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enquiry enquiry = (Enquiry) o;
        return Objects.equals(enquiryID, enquiry.enquiryID) && Objects.equals(customer, enquiry.customer) && Objects.equals(enquiryName, enquiry.enquiryName) && Objects.equals(enquirySubjectLine, enquiry.enquirySubjectLine) && Objects.equals(enquiryBodyContent, enquiry.enquiryBodyContent) && Objects.equals(enquiryDate, enquiry.enquiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enquiryID, customer, enquiryName, enquirySubjectLine, enquiryBodyContent, enquiryDate);
    }

    @Override
    public String
    toString() {
        return "Enquiry{" +
                "enquiryID=" + enquiryID +
                ", customer=" + customer +
                ", enquiryName='" + enquiryName + '\'' +
                ", enquirySubjectLine='" + enquirySubjectLine + '\'' +
                ", enquiryBodyContent='" + enquiryBodyContent + '\'' +
                ", enquiryDate='" + enquiryDate + '\'' +
                '}';
    }

    private Enquiry(Builder b){
        this.enquiryID = b.enquiryID;
        this.customer = b.customer;
        this.enquiryName = b.enquiryName;
        this.enquirySubjectLine = b.enquirySubjectLine;
        this.enquiryBodyContent = b.enquiryBodyContent;
        this.enquiryDate = b.enquiryDate;
    }

    public static class Builder{
        private Long enquiryID;
        private User customer;
        private String enquiryName;
        private String enquirySubjectLine;
        private String enquiryBodyContent;
        private String enquiryDate;

        public Builder setEnquiryID(Long enquiryID){
            this.enquiryID = enquiryID;
            return this;
        }

        public Builder setCustomer(User customer) {
            this.customer = customer;
            return this;
        }

        public Builder setEnquiryName(String enquiryName){
            this.enquiryName = enquiryName;
            return this;
        }

        public Builder setEnquirySubjectLine(String enquirySubjectLine){
            this.enquirySubjectLine = enquirySubjectLine;
            return this;
        }

        public Builder setEnquiryBodyContent(String enquiryBodyContent){
            this.enquiryBodyContent = enquiryBodyContent;
            return this;
        }

        public Builder setEnquiryDate(String enquiryDate) {
            this.enquiryDate = enquiryDate;
            return this;
        }

        public Builder copy(Enquiry enquiry){
            this.enquiryID = enquiry.enquiryID;
            this.customer = enquiry.customer;
            this.enquiryName = enquiry.enquiryName;
            this.enquirySubjectLine = enquiry.enquirySubjectLine;
            this.enquiryBodyContent = enquiry.enquiryBodyContent;
            this.enquiryDate = enquiry.enquiryDate;
            return this;
        }

        public Enquiry build(){
            return new Enquiry(this);
        }
    }
}
