package alp.dev.rentcar.service;


import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.repository.CustomerRepository;
import alp.dev.rentcar.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private PasswordEncoder passwordEncoder; // Şifreleri kontrol etmek için

    public boolean authenticate(String username, String password) {
        Optional<CustomerEntity> customer = customerRepository.findByName(username);
        if (customer == null){
            return false;
        }
        CustomerEntity customerEntity = customer.get();
        return passwordEncoder.matches(password, customerEntity.getPassword());
    }

    public String generateToken(String username) {
        return jwtTokenUtil.generateToken(username);
    }


    public void create(String name, String email, Integer phone, String password) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
        customerRepository.save(customer);
    }

    public void deleteByName(String name){
        customerRepository.removeByName(name);
    }

    public Optional<CustomerEntity> findByName(String name){
        return customerRepository.findByName(name);
    }

    public Optional<CustomerEntity> getCustomerById(Integer id){
        return customerRepository.findById(id);
    }

    public List<CustomerEntity> findAll(){
        return customerRepository.findAll();
    }
}
