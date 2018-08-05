package edu.mum.cs.cs425.ahacarrentalservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerApplication;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarOwnerApplicationRepository;
import edu.mum.cs.cs425.ahacarrentalservice.service.ICarOwnerApplicationService;

@Service("carOwnerApplicationService")
public class CarOwnerApplicationService implements ICarOwnerApplicationService {
	
	@Autowired
	ICarOwnerApplicationRepository coaRepository;

	@Override
	public List<CarOwnerApplication> getPendingApplications() {
		// TODO Auto-generated method stub
		return coaRepository.findPendingApplications();
	}
	
	@Override
	public List<CarOwnerApplication> getApplications() {
		// TODO Auto-generated method stub
		return coaRepository.findAll();
	}

	@Override
	public CarOwnerApplication saveApplication(CarOwnerApplication coa) {
		// TODO Auto-generated method stub
		System.out.println(coa.toString());
		coa.setStatus("Waiting for approval");
		return coaRepository.save(coa);
	}

	@Override
	public CarOwnerApplication getApplication(long coaId) {
		// TODO Auto-generated method stub
		Optional<CarOwnerApplication> result = coaRepository.findById(coaId);
		return result.orElse(null);
	}

	@Override
	public CarOwnerApplication approveApplication(CarOwnerApplication coa) {
		// TODO Auto-generated method stub
		coa.setStatus("Approved");
		return coaRepository.save(coa);
	}
	
}
