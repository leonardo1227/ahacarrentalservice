package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.Rental;
import edu.mum.cs.cs425.ahacarrentalservice.repository.IRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements IService<Rental> {
    @Autowired
    private IRentalRepository repository;


    @Override
    public List<Rental> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Rental> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public Rental findById(Long id) {
        Optional<Rental> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Rental save(Rental rental) {
        return repository.save(rental);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
