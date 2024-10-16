package alp.dev.rentcar.repository;

import alp.dev.rentcar.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByName(String name);
    void removeByName(String name);
}
