package alp.dev.rentcar.repository;


import alp.dev.rentcar.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    Optional<CarEntity> findById(Integer id);
    List<CarEntity> findByMerchantId(Integer merchantId);
    List<CarEntity> findByCustomerId(Integer customerId);
}
