package alp.dev.rentcar.service;


import alp.dev.rentcar.Roles.UserRole;
import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void create(String name, String email, Integer phone, String password) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .userRole(UserRole.CUSTOMER)
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

    public CustomerEntity findByEmail(String email) {
        return customerRepository.findByEmail(email).get();
    }
}
