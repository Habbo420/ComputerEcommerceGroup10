package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SalesItem;
import za.ac.cput.repository.SalesItemRepository;
import za.ac.cput.service.SalesItemService;

import java.util.List;

@Service
public class SalesItemServiceImpl implements SalesItemService {

    private SalesItemRepository repository;

    @Autowired
    private SalesItemServiceImpl(SalesItemRepository repository){
        this.repository = repository;
    }
    @Override
    public SalesItem create(SalesItem salesItem) {
        return this.repository.save(salesItem);
    }

    @Override
    public SalesItem read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public SalesItem update(SalesItem salesItem) {
        if(this.repository.existsById(salesItem.getSalesItemID()))
            return this.repository.save(salesItem);
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<SalesItem> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<SalesItem> findBySales_SaleID(Long salesId) {
        return repository.findBySales_SaleID(salesId);
    }
}
