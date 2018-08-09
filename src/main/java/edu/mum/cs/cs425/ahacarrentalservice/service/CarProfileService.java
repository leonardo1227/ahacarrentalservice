package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarProfileService implements ICarProfileService<CarProfile> {
    @Autowired
    private ICarProfileRepository repository;

    
    
    public List<CarProfile> findAll(){
        return repository.findAll();
    }

    @Override
    public List<CarProfile> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public CarProfile findById(Long id) {
        Optional<CarProfile> car = repository.findById(id);
        return car.orElse(null);
    }

    @Override
    public CarProfile save(CarProfile car) {
        return repository.save(car);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
