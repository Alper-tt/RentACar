package alp.dev.rentcar.controller;


import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.entity.MerchantEntity;
import alp.dev.rentcar.service.CarService;
import alp.dev.rentcar.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final MerchantService merchantService;

    //@GetMapping
    //public Optional<CarEntity> getCarById(@RequestParam Integer id) {
    //    return carService.getCarById(id);
    //}

    @GetMapping
    public List<CarEntity> findAll() {
        return carService.getAllCars();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String brand, @RequestParam String model, @RequestParam String color, @RequestParam Integer carYear) {
        carService.create(brand, model, color, carYear);
        return ResponseEntity.ok("car created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        carService.deleteById(id);
        return ResponseEntity.ok("car deleted");
    }

}
