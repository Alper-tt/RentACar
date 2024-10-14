package alp.dev.rentcar.repository;

import alp.dev.rentcar.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByFirstName(String firstName);
    void removeByFirstName(String firstName);
}
