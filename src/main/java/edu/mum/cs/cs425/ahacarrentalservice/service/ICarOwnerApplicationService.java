package edu.mum.cs.cs425.ahacarrentalservice.service;

import java.util.List;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerApplication;

public interface ICarOwnerApplicationService {
	public abstract List<CarOwnerApplication> getPendingApplications();
	public abstract List<CarOwnerApplication> getApplications();
	public abstract CarOwnerApplication saveApplication(CarOwnerApplication coa);
	public abstract CarOwnerApplication getApplication(long coaId);
	public abstract CarOwnerApplication approveApplication(CarOwnerApplication coa);
}
