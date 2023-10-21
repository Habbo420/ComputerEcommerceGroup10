package za.ac.cput.repository;

/* CityRepository.java
Repository class for City.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.City;


@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
