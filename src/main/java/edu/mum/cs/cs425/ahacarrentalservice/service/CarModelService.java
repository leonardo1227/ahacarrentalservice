package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarModel;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelService implements IService<CarModel> {
    @Autowired
    private ICarModelRepository repository;
    @Override
    public List<CarModel> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CarModel> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public CarModel findById(Long id) {
        Optional<CarModel> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public CarModel save(CarModel carModel) {
        return repository.save(carModel);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
