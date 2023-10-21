// RoleRepository.java
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Role;
import za.ac.cput.domain.RoleName;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}