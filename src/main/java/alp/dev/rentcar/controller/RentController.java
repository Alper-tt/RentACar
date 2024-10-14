package alp.dev.rentcar.controller;

import alp.dev.rentcar.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @PostMapping("/start")
    public ResponseEntity<String> addRent(@RequestParam Integer merchantId, Integer customerId, Integer carId){
        rentService.rentMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("car rented successfully");
    }

    @DeleteMapping("/finish")
    public ResponseEntity<String> finishRent(@RequestParam Integer customerId, @RequestParam Integer carId){
        rentService.rentFinishedMerchantCarToCustomer(customerId, carId);
        return ResponseEntity.ok("rent finished");
    }
}
