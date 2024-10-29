package alp.dev.rentcar.controller;

import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @GetMapping
    @PreAuthorize("hasRole('MERCHANT')")
    public List<CarEntity> getAllRents(){
        return rentService.getAllRentedMerchantCars();
    }

    @PostMapping("/start")
    @PreAuthorize("hasAnyRole('MERCHANT', 'CUSTOMER')")
    public ResponseEntity<String> addRent(@RequestParam Integer customerId, Integer carId){
        rentService.rentMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("Car rented successfully");
    }

    @DeleteMapping("/finish")
    @PreAuthorize("hasAnyRole('MERCHANT', 'CUSTOMER')")
    public ResponseEntity<String> finishRent(@RequestParam Integer customerId, @RequestParam Integer carId){
        rentService.rentFinishedMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("Rent finished");
    }
}
