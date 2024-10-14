package alp.dev.rentcar.service;


import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void create(String firstName, String lastName, String email, Integer phone) {
        CustomerEntity customer = CustomerEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .build();
        customerRepository.save(customer);
    }

    public void deleteByName(String firstName){
        customerRepository.removeByFirstName(firstName);
    }

    public Optional<CustomerEntity> findByFirstName(String firstName){
        return customerRepository.findByFirstName(firstName);
    }

    public Optional<CustomerEntity> getCustomerById(Integer id){
        return customerRepository.findById(id);
    }

    public List<CustomerEntity> findAll(){
        return customerRepository.findAll();
    }

}
