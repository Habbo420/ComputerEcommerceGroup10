package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Invoice;
import za.ac.cput.repository.InvoiceRepository;
import za.ac.cput.service.InvoiceService;

import java.util.List;
import java.util.Set;

/*
    InvoiceServiceImpl.java
    Author: Alexander Draai - 220118744
    This is the implementation for the service InvoiceHistoryServiceImpl.java
    Date: 10 - 06 - 2023
 */

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;


    @Override
    public Invoice create(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public Invoice read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public boolean delete(String id) {
        if (this.repository.existsById(id)){
            this.repository.deleteById(id);
            return  true;
        }
        return false;
    }

    @Override
    public List<Invoice> getAll() {
        return repository.findAll();
    }
}
