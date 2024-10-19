package alp.dev.rentcar.service;

import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.model.request.LoginRequest;
import alp.dev.rentcar.repository.CustomerRepository;
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


    public void registerCustomer(LoginRequest loginRequest) {
        customerRepository.save(
                CustomerEntity.builder()
                        .email(loginRequest.getEmail())
                        .password(passwordEncoder.encode(loginRequest.getPassword()))
                        .build());
    }

    public String loginCustomer(LoginRequest loginRequest) {
        CustomerEntity customerEntity = customerRepository.findByEmail(loginRequest.getEmail()).get();
        if (passwordEncoder.matches(loginRequest.getPassword(), customerEntity.getPassword())) {
            System.out.println("gidi");
            return tokenUtils.createToken(customerEntity.getId());
        }
        System.out.println("null");
        return null;
    }
}
