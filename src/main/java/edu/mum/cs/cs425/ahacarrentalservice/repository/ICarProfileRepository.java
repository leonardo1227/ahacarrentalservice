package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarProfileRepository extends JpaRepository<CarProfile, Long> {

    Boolean existsByPlate(String plate);
    Boolean existsByPlateAndIdNot(String plate, Long id);
}
