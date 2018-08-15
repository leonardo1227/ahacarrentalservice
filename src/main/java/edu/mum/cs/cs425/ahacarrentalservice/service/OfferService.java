package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.*;
import edu.mum.cs.cs425.ahacarrentalservice.repository.IOfferRepository;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
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
    public Offer save(Offer offer) throws ValidationException {
        if(offer.getCarProfile().getStatus()!= AnalysisStatus.APPROVED){
            throw new ValidationException("Car Profile must be approved to register or alter any offer to it!");
        }
        return repository.save(offer);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Offer> findByCarProfile(CarProfile carProfile){
        return repository.findByCarProfileOrderByIdAsc(carProfile);
    }

    public List<Offer> findAllAvailables(){
        return repository.findByPublicationStatusAndAnalysisStatusCarProfile(true, AnalysisStatus.APPROVED);
    }


    public List<Offer> findAllAvailableByCarBrand(CarBrand carBrand){
        return repository.findByBrandCarProfileAndAnalysisStatusCarProfile(true, carBrand, AnalysisStatus.APPROVED);
    }

    public List<Offer> findAllAvailablesByCarModel(CarModel carModel){
        return repository.findByModelCarProfileAndAnalysisStatusCarProfile(true, carModel, AnalysisStatus.APPROVED);
    }

    public List<Offer> findAllAvailablesByYear(Integer year){
        return  repository.findByYearCarProfileAndAnalysisStatusCarProfile(true, year,AnalysisStatus.APPROVED);
    }

    public List<Offer> findAllAvailablesByYearAndModel(Integer year, CarModel carModel){
        return repository.findByYearAndModelCarProfileAndAnalysisStatusCarProfile(true, year, carModel, AnalysisStatus.APPROVED);
    }

    public List<Offer> findAllAvailableByYearAndBrand(Integer year, CarBrand carBrand){
        return repository.findByYearAndBrandCarProfileAndAnalysisStatusCarProfile(true,year,carBrand,AnalysisStatus.APPROVED);
    }


//    public List<Offer> filterOffers(int brandId, int modelId , int year){
//    public List<Offer> findByAvailablesByCarBrandCarBrand(CarBrand carBrand, CarModel carModel, Integer year){

//        if(carBrand==null && )


       /* List<Offer> offers = findAll().stream()
//                .filter(x -> x.getStatus().getValue() == CarStatus.APPROVED.getValue())
//                .filter(x -> x.getStatus() == AnalysisStatus.APPROVED)
                .filter(x -> x.getCarProfile().getModel().getId() == modelId || modelId == 0    )
                .filter(x -> x.getCarProfile().getModel().getBrand().getId() == brandId || brandId == 0)
                .filter(x -> x.getCarProfile().getYear() == year || year == 0)
                .collect(Collectors.toList());
        return offers;*/
//    }
}
