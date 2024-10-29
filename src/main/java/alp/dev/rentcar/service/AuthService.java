package alp.dev.rentcar.service;

import alp.dev.rentcar.Roles.UserRole;
import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.entity.MerchantEntity;
import alp.dev.rentcar.model.request.LoginRequest;
import alp.dev.rentcar.repository.CustomerRepository;
import alp.dev.rentcar.repository.MerchantRepository;
import alp.dev.rentcar.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final MerchantRepository merchantRepository;


    public void register(LoginRequest loginRequest) {
        UserRole userRole = UserRole.valueOf(loginRequest.getUserRole().toUpperCase());

        if (userRole == UserRole.CUSTOMER) {
            customerRepository.save(
                    CustomerEntity.builder()
                            .name(loginRequest.getUsername())
                            .email(loginRequest.getEmail())
                            .password(passwordEncoder.encode(loginRequest.getPassword()))
                            .userRole(userRole)
                            .build());
        } else if (userRole == UserRole.MERCHANT) {
            merchantRepository.save(
                    MerchantEntity.builder()
                            .name(loginRequest.getUsername())
                            .email(loginRequest.getEmail())
                            .password(passwordEncoder.encode(loginRequest.getPassword()))
                            .userRole(userRole)
                            .build());
        } else {
            throw new IllegalArgumentException("Invalid role provided: " + loginRequest.getUserRole());
        }
    }

    public String login(LoginRequest loginRequest) {
        UserRole userRole = UserRole.valueOf(loginRequest.getUserRole().toUpperCase());

        if (userRole == UserRole.CUSTOMER) {
            CustomerEntity customerEntity = customerRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (passwordEncoder.matches(loginRequest.getPassword(), customerEntity.getPassword())) {
                return tokenUtils.createToken(customerEntity.getId(), userRole.name());
            }
        } else if (userRole == UserRole.MERCHANT) {
            MerchantEntity merchantEntity = merchantRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Merchant not found"));
            if (passwordEncoder.matches(loginRequest.getPassword(), merchantEntity.getPassword())) {
                return tokenUtils.createToken(merchantEntity.getId(), userRole.name());
            }
        } else {
            throw new IllegalArgumentException("Invalid role provided: " + loginRequest.getUserRole());
        }
        return null;
    }

}