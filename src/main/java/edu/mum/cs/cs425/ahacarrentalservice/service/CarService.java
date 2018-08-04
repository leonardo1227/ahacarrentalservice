package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.Car;
import edu.mum.cs.cs425.ahacarrentalservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarService implements IService<Car> {
    @Autowired
    private CarRepository repository;

    public List<Car> findAll(){
        return repository.findAll();
    }

    public Car save(Car car){
        return repository.save(car);
    }


}
