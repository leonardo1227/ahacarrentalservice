package edu.mum.cs.cs425.ahacarrentalservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.ProfileStatus;
import edu.mum.cs.cs425.ahacarrentalservice.repository.ICarOwnerProfileRepository;
import edu.mum.cs.cs425.ahacarrentalservice.service.ICarOwnerProfileService;

@Service("carOwnerProfileService")
public class CarOwnerProfileService implements ICarOwnerProfileService {
	
	@Autowired
	ICarOwnerProfileRepository carOwnerProfileRepository;

	@Override
	public List<CarOwnerProfile> findPendingApproveProfiles() {
		// TODO Auto-generated method stub
		return carOwnerProfileRepository.findAll(); //.findPendingApplications();
	}
	
	@Override
	public List<CarOwnerProfile> findAll() {
		// TODO Auto-generated method stub
		return carOwnerProfileRepository.findAll();
	}

	@Override
	public CarOwnerProfile create(CarOwnerProfile cop) {
		// TODO Auto-generated method stub
		cop.setStatus(ProfileStatus.PENDING);
		return carOwnerProfileRepository.save(cop);
	}

	@Override
	public CarOwnerProfile findById(long id) {
		// TODO Auto-generated method stub
		Optional<CarOwnerProfile> result = carOwnerProfileRepository.findById(id);
		return result.orElse(null);
	}

	@Override
	public CarOwnerProfile approveProfile(CarOwnerProfile cop) {
		// TODO Auto-generated method stub
		cop.setStatus(ProfileStatus.APPROVED);
		return carOwnerProfileRepository.save(cop);
	}
	
}
