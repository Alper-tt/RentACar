package alp.dev.rentcar.controller;

import alp.dev.rentcar.entity.MerchantEntity;
import alp.dev.rentcar.model.response.MerchantCarListResponse;
import alp.dev.rentcar.service.CarService;
import alp.dev.rentcar.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    private final CarService carService;

    @PostMapping("/addCar")
    public ResponseEntity<String> addCar(@RequestParam String merchantName, @RequestParam Integer carId) {
        carService.addCarToMerchant(merchantName, carId);
        return ResponseEntity.ok("Car added to merchant successfully");
    }

    @GetMapping("/{merchantId}/cars")
    public MerchantCarListResponse getMerchantCars(@PathVariable Integer merchantId) {
        return carService.getCarsByMerchantId(merchantId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String name, @RequestParam String email) {
        merchantService.create(name, email);
        return ResponseEntity.ok("created");
    }

    @GetMapping
    public List<MerchantEntity> getAllMerchants() {
        return merchantService.getMerchants();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteByName(@RequestParam String name) {
        merchantService.deleteByName(name);
        return ResponseEntity.ok("deleted by name");
    }
}
