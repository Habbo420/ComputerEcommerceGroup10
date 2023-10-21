package za.ac.cput.repository;

/* SalesRepository.java
Repository for the Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
