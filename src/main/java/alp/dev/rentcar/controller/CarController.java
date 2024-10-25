package alp.dev.rentcar.controller;


import alp.dev.rentcar.Roles.UserRole;
import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    @PreAuthorize("hasRole('MERCHANT')")
    public List<CarEntity> findAll() {
        return carService.getAllCars();
    }

    @PostMapping()
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<String> create(@RequestParam String brand, @RequestParam String model, @RequestParam String color, @RequestParam Integer carYear) {
        carService.create(brand, model, color, carYear);
        return ResponseEntity.ok("car created");
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        carService.deleteById(id);
        return ResponseEntity.ok("car deleted");
    }
}
