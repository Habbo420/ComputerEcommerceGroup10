package za.ac.cput.repository;

/* CountryRepository.java
Repository class for Country.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
