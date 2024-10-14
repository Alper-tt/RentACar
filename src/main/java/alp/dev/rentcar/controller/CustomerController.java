package alp.dev.rentcar.controller;


import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.model.response.CustomerCarListResponse;
import alp.dev.rentcar.service.CarService;
import alp.dev.rentcar.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;

    @GetMapping
    public List<CustomerEntity> getAllCustomers(){
        return customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam Integer phone) {
        customerService.create(firstName, lastName, email, phone);
        return ResponseEntity.ok("Customer created");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String firstName) {
        customerService.deleteByName(firstName);
        return ResponseEntity.ok("Customer deleted");
    }

    @GetMapping("/{customerId}/cars")
    public CustomerCarListResponse getMerchantCars(@PathVariable Integer customerId) {
        return carService.getCarsByCustomerId(customerId);
    }
}
