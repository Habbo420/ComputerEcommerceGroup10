package za.ac.cput.service;
/* ProductService.java
 Service layer for the ProductService
 Author: Reece Bergstedt - 221075240
 Date: 11 June 2023
*/
import za.ac.cput.domain.Product;

import java.util.List;
import java.util.Set;

public interface ProductService extends IService<Product, Long>{
    List<Product> getAll();
}
