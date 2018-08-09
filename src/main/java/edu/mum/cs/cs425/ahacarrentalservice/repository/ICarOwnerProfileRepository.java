package edu.mum.cs.cs425.ahacarrentalservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.ProfileStatus;

@Repository("carOwnerProfileRepository")
public interface ICarOwnerProfileRepository extends JpaRepository<CarOwnerProfile, Long> {
	// Relies on the default public abstract methods defined in the super interface, JpaRepository<T, ID>
	// We may override or add more methods here, if needed
//	@Query(value="select * from carownerapplications where carownerapplications.status != 'Approved'", nativeQuery = true)
	List<CarOwnerProfile> existsByStatus(ProfileStatus status);
	
//	@Query(value="select carownerapplications.userId from carownerapplications where carownerapplications.userId = userId", nativeQuery = true)
//	Optional<String> findByUserId(@Param("userId") String userId);
	
	Boolean existsByUserId(String userId);
}
