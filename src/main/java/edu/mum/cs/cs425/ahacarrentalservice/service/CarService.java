package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.Car;
import edu.mum.cs.cs425.ahacarrentalservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements IService<Car> {
    @Autowired
    private CarRepository repository;

    public List<Car> findAll(){
        return repository.findAll();
    }

    @Override
    public List<Car> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = repository.findById(id);
        return car.orElse(null);
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
