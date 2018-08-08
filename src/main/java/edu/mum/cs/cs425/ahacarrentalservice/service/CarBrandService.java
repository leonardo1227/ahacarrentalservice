package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarBrand;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandService implements IService<CarBrand> {
   @Autowired
   private ICarBrandRepository repository;

    @Override
    public List<CarBrand> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CarBrand> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public CarBrand findById(Long id) {
        Optional<CarBrand> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public CarBrand save(CarBrand carBrand) {
        return repository.save(carBrand);
    }

    @Override
    public void deleteById(Long id) {
    }
}
