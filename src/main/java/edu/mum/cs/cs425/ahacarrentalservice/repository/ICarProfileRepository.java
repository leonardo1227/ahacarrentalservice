package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.AnalysisStatus;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarProfileRepository extends JpaRepository<CarProfile, Long> {

    Boolean existsByPlate(String plate);
    Boolean existsByPlateAndIdNot(String plate, Long id);
    List<CarProfile> findByCarOwnerProfile(CarOwnerProfile carOwnerProfile);
    List<CarProfile> findByStatus(AnalysisStatus analysisStatus);
}
