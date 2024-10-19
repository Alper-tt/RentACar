package alp.dev.rentcar.controller;


import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.model.request.LoginRequest;
import alp.dev.rentcar.model.response.CustomerCarListResponse;
import alp.dev.rentcar.model.response.JwtResponse;
import alp.dev.rentcar.service.CarService;
import alp.dev.rentcar.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;

    @GetMapping
    public List<CustomerEntity> getAllCustomers(){
        return customerService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestParam String name, @RequestParam String email, @RequestParam Integer phone, @RequestParam String password) {
        customerService.create(name, email, phone, password);
        return ResponseEntity.ok("Customer created");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String name) {
        customerService.deleteByName(name);
        return ResponseEntity.ok("Customer deleted");
    }

    @GetMapping("/{customerId}/cars")
    public CustomerCarListResponse getMerchantCars(@PathVariable Integer customerId) {
        return carService.getCarsByCustomerId(customerId);
    }
}
