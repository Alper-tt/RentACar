package alp.dev.rentcar.controller;

import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @GetMapping
    public List<CarEntity> getAllRents(){
        return rentService.getAllRentedMerchantCars();
    }

    @PostMapping("/start")
    public ResponseEntity<String> addRent(@RequestParam Integer customerId, Integer carId){
        rentService.rentMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("car rented successfully");
    }

    @DeleteMapping("/finish")
    public ResponseEntity<String> finishRent(@RequestParam Integer customerId, @RequestParam Integer carId){
        rentService.rentFinishedMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("rent finished");
    }
}
