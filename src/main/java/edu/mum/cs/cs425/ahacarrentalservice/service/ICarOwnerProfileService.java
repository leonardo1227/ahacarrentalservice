package edu.mum.cs.cs425.ahacarrentalservice.service;

import java.util.List;
import java.util.Optional;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;

public interface ICarOwnerProfileService {
	public abstract List<CarOwnerProfile> findPendingApproveProfiles();
	public abstract List<CarOwnerProfile> findAll();
	public abstract CarOwnerProfile create(CarOwnerProfile profile);
	public abstract CarOwnerProfile findById(long id);
	public abstract CarOwnerProfile approveProfile(CarOwnerProfile profile);
	public abstract Boolean findByUserId(String userId);
}
