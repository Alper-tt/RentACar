package alp.dev.rentcar.service;

import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final CarRepository carRepository;
    private final CarService carService;
    private final CustomerService customerService;

    public void rentMerchantCarToCustomer(Integer customerId, Integer carId) {
        Optional<CarEntity> car = carService.getCarById(carId);
        Optional<CustomerEntity> customer = customerService.getCustomerById(customerId);

        CustomerEntity customerEntity = customer.get();
        CarEntity carEntity = car.get();
        carEntity.setCustomer(customerEntity);
        carRepository.save(carEntity);
    }

    public void rentFinishedMerchantCarToCustomer(Integer customerId, Integer carId) {
        Optional<CarEntity> car = carService.getCarById(carId);
        Optional<CustomerEntity> customer = customerService.getCustomerById(customerId);
        CustomerEntity customerEntity = customer.get();
        CarEntity carEntity = car.get();
        carEntity.setCustomer(null);
        carRepository.save(carEntity);
    }

    public List<CarEntity> getAllRentedMerchantCars() {
        List<CarEntity> allCars = carService.getAllCars();
        List<CarEntity> rentedCars = new ArrayList<CarEntity>();
        for (CarEntity carEntity : allCars) {
            if (carEntity.getCustomer() != null) {
                rentedCars.add(carEntity);
            }
        }
        return rentedCars;
    }

}
