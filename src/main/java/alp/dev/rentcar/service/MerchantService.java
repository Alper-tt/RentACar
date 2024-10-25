package alp.dev.rentcar.service;

import alp.dev.rentcar.Roles.UserRole;
import alp.dev.rentcar.entity.MerchantEntity;
import alp.dev.rentcar.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public void create(String name, String email) {
        MerchantEntity merchant = MerchantEntity.builder()
                .name(name)
                .email(email)
                .userRole(UserRole.MERCHANT)
                .build();

        merchantRepository.save(merchant);
    }

    public void deleteByName(String name) {
        merchantRepository.removeByName(name);
    }

    public Optional<MerchantEntity> getMerchantByName(String name) {
        return merchantRepository.findByName(name);
    }

    public Optional<MerchantEntity> getMerchantById(Integer id) {
        return merchantRepository.findById(id);
    }

    public List<MerchantEntity> getMerchants() {
        return merchantRepository.findAll();
    }
}
