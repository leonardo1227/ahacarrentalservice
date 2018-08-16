package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.AnalysisStatus;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarProfileRepository;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarProfileService implements IService<CarProfile> {
    @Autowired
    private ICarProfileRepository repository;

    public List<CarProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CarProfile> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC, orderingProperty));
    }

    public List<CarProfile> findByCarOwnerProfile(CarOwnerProfile carOwnerProfile){
        return repository.findByCarOwnerProfile(carOwnerProfile);
    }

    public List<CarProfile> findByStatus(AnalysisStatus analysisStatus){
        return repository.findByStatus(analysisStatus);
    }

    @Override
    public CarProfile findById(Long id) {
        Optional<CarProfile> car = repository.findById(id);
        return car.orElse(null);
    }

    @Override
    public CarProfile save(CarProfile carProfile) throws ValidationException{
        if(verifyIfPlateIsAlreadyRegistered(carProfile.getPlate(),carProfile.getId())){
            throw new ValidationException("Already there is a Car Profile registered with the informed plate");
        }
        return repository.save(carProfile);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Boolean verifyIfPlateIsAlreadyRegistered(String plate, Long id){
        if(id==null){
            return repository.existsByPlate(plate);
        }else{
            return repository.existsByPlateAndIdNot(plate, id);
        }
    }

}
