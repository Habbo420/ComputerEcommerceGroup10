package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;
/*
    Author: Alexander Draai
    Date: 02 - 04 - 2022
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
