package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Invoice;

import java.util.List;
/*
    InvoiceService.java
    Author: Alexander Draai - 220118744
    This is the Service for InvoiceService.java
    Date: 10 - 06 - 2023
 */

public interface InvoiceService extends IService <Invoice, Long>{
    List<Invoice> getAll();

}
