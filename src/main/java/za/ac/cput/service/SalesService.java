package za.ac.cput.service;

/* SalesService.java
Service for the Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import za.ac.cput.domain.Sales;
import za.ac.cput.dto.SalesDto;

import java.util.List;

public interface SalesService extends IService<Sales, Long>{
    List<Sales> getAll();

    Sales createSale(SalesDto salesDto);

    SalesDto convertToSalesDto(Sales sales);
}
