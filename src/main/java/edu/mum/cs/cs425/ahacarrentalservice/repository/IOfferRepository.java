package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCarProfile(CarProfile carProfile);

}
