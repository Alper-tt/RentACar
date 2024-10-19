package alp.dev.rentcar.repository;


import alp.dev.rentcar.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Integer> {
    Optional<MerchantEntity> findByName(String name);
    void removeByName(String name);
}
