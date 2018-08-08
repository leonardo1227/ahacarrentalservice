package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.Offer;
import edu.mum.cs.cs425.ahacarrentalservice.repository.IOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService implements IService<Offer> {
    @Autowired
    private IOfferRepository repository;


    @Override
    public List<Offer> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Offer> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    @Override
    public Offer findById(Long id) {
        Optional<Offer> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Offer save(Offer offer) {
        return repository.save(offer);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Offer> findByCarProfile(CarProfile carProfile){
        return repository.findByCarProfile(carProfile);
    }
}
