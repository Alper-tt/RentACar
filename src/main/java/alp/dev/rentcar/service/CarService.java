package alp.dev.rentcar.service;


import alp.dev.rentcar.entity.CarEntity;
import alp.dev.rentcar.entity.CustomerEntity;
import alp.dev.rentcar.entity.MerchantEntity;
import alp.dev.rentcar.model.response.CustomerCarListResponse;
import alp.dev.rentcar.model.response.CustomerCarResponse;
import alp.dev.rentcar.model.response.MerchantCarListResponse;
import alp.dev.rentcar.model.response.MerchantCarResponse;
import alp.dev.rentcar.repository.CarRepository;
import alp.dev.rentcar.repository.CustomerRepository;
import alp.dev.rentcar.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final MerchantRepository merchantRepository;
    private final CustomerRepository customerRepository;

    public void create(String brand, String model, String color, Integer carYear) {
        CarEntity car = CarEntity.builder()
                .brand(brand)
                .model(model)
                .color(color)
                .carYear(carYear)
                .build();
        carRepository.save(car);
    }

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    public Optional<CarEntity> getCarById(Integer id) {
        return carRepository.findById(id);
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public void addCarToMerchant(String merchantName, Integer carId) {
        Optional<MerchantEntity> optionalMerchant = merchantRepository.findByName(merchantName);
        if (optionalMerchant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant not found");
        }
        MerchantEntity merchant = optionalMerchant.get();

        Optional<CarEntity> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        CarEntity car = optionalCar.get();
        car.setMerchant(merchant);
        carRepository.save(car);
    }

    public void addCarToCustomer(String customerName, Integer carId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByFirstName(customerName);
        if (optionalCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        CustomerEntity customer = optionalCustomer.get();
        Optional<CarEntity> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        CarEntity car = optionalCar.get();
        car.setCustomer(customer);
        carRepository.save(car);
    }

    public CustomerCarListResponse getCarsByCustomerId(Integer customerId) {
        List<CarEntity> carEntityList = carRepository.findByCustomerId(customerId);
        List<CustomerCarResponse> customerCarResponseList = new ArrayList<CustomerCarResponse>();

        return CustomerCarListResponse.builder().customerCars(carEntityList.stream().map(carEntity ->
                CustomerCarResponse.builder()
                        .id(carEntity.getId())
                        .year(carEntity.getCarYear())
                        .brand(carEntity.getBrand())
                        .model(carEntity.getModel())
                        .color(carEntity.getColor())
                        .build()).toList())
                .build();

    }

    public MerchantCarListResponse getCarsByMerchantId(Integer merchantId) {
        List<CarEntity> carEntityList = carRepository.findByMerchantId(merchantId);
        List<MerchantCarResponse> merchantCarResponseList = new ArrayList<MerchantCarResponse>();

//        for (CarEntity carEntity : carEntityList) {
//            MerchantCarResponse merchantCarResponse = new MerchantCarResponse();
//            merchantCarResponse.setId(carEntity.getId());
//            merchantCarResponse.setBrand(carEntity.getBrand());
//            merchantCarResponse.setModel(carEntity.getModel());
//            merchantCarResponse.setColor(carEntity.getColor());
//            merchantCarResponse.setYear(carEntity.getCarYear());
//            merchantCarResponseList.add(merchantCarResponse);
//        }
//        MerchantCarListResponse merchantCarListResponse = new MerchantCarListResponse();
//        merchantCarListResponse.setMerchantCars(merchantCarResponseList);
//        return merchantCarListResponse;

        return MerchantCarListResponse.builder()
                .merchantCars(carEntityList.stream().map(carEntity ->
                        MerchantCarResponse.builder()
                                .id(carEntity.getId())
                                .year(carEntity.getCarYear())
                                .brand(carEntity.getBrand())
                                .model(carEntity.getModel())
                                .color(carEntity.getColor())
                                .build()).toList())
                .build();
    }

}
