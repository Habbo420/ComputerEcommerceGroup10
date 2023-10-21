package za.ac.cput.service;
/*
Author: Hanno Visser Immelman 221074414
*/
import za.ac.cput.domain.StoreDetails;

import java.util.List;
import java.util.Set;

/*
    StoreDetailsService.java
    Author: Hanno Visser Immelman 221074414
    This is the Service for the service the StoreDetails entity
    Date: 10 - 06 - 2023
 */

public interface StoreDetailsService extends IService<StoreDetails, Long> {
    List<StoreDetails> getAll();
}
