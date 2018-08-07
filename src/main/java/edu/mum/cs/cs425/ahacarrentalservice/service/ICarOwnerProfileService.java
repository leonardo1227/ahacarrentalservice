package edu.mum.cs.cs425.ahacarrentalservice.service;

import java.util.List;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;

public interface ICarOwnerProfileService {
	public abstract List<CarOwnerProfile> findPendingApproveProfiles();
	public abstract List<CarOwnerProfile> findAll();
	public abstract CarOwnerProfile create(CarOwnerProfile coa);
	public abstract CarOwnerProfile findById(long coaId);
	public abstract CarOwnerProfile approveProfile(CarOwnerProfile coa);
}
