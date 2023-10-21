package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SalesItem;

import java.util.List;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Long> {
    List<SalesItem> findBySales_SaleID(Long salesId);
}
