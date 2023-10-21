package za.ac.cput.service;

import za.ac.cput.domain.Country;
import za.ac.cput.domain.SalesItem;

import java.util.List;

public interface SalesItemService extends IService<SalesItem, Long>{
    List<SalesItem> getAll();

    List<SalesItem> findBySales_SaleID(Long salesId);
}
