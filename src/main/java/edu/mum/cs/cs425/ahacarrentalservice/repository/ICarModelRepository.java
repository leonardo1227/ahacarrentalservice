package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarModelRepository extends JpaRepository<CarModel, Long> {
}
