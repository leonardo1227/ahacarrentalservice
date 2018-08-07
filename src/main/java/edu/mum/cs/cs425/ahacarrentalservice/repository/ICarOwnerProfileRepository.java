package edu.mum.cs.cs425.ahacarrentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;

@Repository("carOwnerProfileRepository")
public interface ICarOwnerProfileRepository extends JpaRepository<CarOwnerProfile, Long> {
	// Relies on the default public abstract methods defined in the super interface, JpaRepository<T, ID>
	// We may override or add more methods here, if needed
	@Query(value="select * from carownerapplications where carownerapplications.status != 'Approved'", nativeQuery = true)
	List<CarOwnerProfile> findPendingApplications();
}
