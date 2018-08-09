package edu.mum.cs.cs425.ahacarrentalservice.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.model.ProfileStatus;
import edu.mum.cs.cs425.ahacarrentalservice.service.ICarOwnerProfileService;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;

@ManagedBean(value="carOwnerProfile")
@SessionScoped
public class CarOwnerProfileController implements Serializable, IController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154610627490123470L;

	@Autowired
	private ICarOwnerProfileService carOwnerProfileService;

	private List<CarOwnerProfile> profiles;

	// placeholder variable for creating new profile
	private CarOwnerProfile newProfile;
	
	private CarOwnerProfile selectedProfile;
	private ProfileStatus selectedStatus;

	public List<CarOwnerProfile> getProfiles() {
		profiles = carOwnerProfileService.findPendingApproveProfiles();
		return profiles;
	}

	public void setProfiles(List<CarOwnerProfile> profiles) {
		this.profiles = profiles;
	}
	
	public CarOwnerProfile getNewProfile() {
		if(newProfile == null) {
			newProfile = new CarOwnerProfile();
			Calendar cal = Calendar.getInstance();
			cal.set(1988, Calendar.JANUARY, 10);
		
//			newProfile.setUserId("user1");
//			newProfile.setPassword("123");
			newProfile.setFirstName("FirstName");
			newProfile.setLastName("LastName");
			newProfile.setDob(cal.getTime());
			newProfile.setEmailAddress("test@mail.com");
			newProfile.setPhone("111-111-1111");
			newProfile.setAddress("1000 N 4th St. Fairfield, IA 52557");
//			newProfile.setStatus(ProfileStatus.PENDING);
		}
		
		return newProfile;
	}

	public void setNewProfile(CarOwnerProfile newProfile) {
		this.newProfile = newProfile;
	}

	public CarOwnerProfile getSelectedProfile() {
		return selectedProfile;
	}

	public void setSelectedProfile(CarOwnerProfile selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
	
	public ProfileStatus[] getStatuses() {
		return ProfileStatus.values();
	}
	
	public ProfileStatus getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(ProfileStatus selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	@PostConstruct
	private void postConstruct() {
//		newProfile = new CarOwnerProfile();
//		profiles = carOwnerProfileService.findPendingApproveProfiles();
	}
	
	public void checkAvailableUserId() {
		System.out.println(newProfile.getUserId());
		if(carOwnerProfileService.findByUserId(newProfile.getUserId())) {
			String message = "The user id '" + newProfile.getUserId() + "' is already used by other. Please choose another.";
			showMessage(message, message, InformationType.ERROR);
		}
	}
	
	public String createNewProfile() {
		System.out.println(newProfile.toString());
		try {
		carOwnerProfileService.create(newProfile);
        newProfile = null;
        setProfiles(carOwnerProfileService.findPendingApproveProfiles());
		} catch (ValidationException e) {
			// TODO: handle exception
			showMessage(e.getMessage(), e.getMessage(), InformationType.ERROR);
		}
        return "/system/car_owner/browse?faces-redirect=true";
    }
	
	public String viewProfile() {
		if(selectedProfile == null || selectedProfile.getId() == null) {
			String message = "Please select a profile";
			showMessage(message, null, InformationType.INFORMATION);
			return null;
		}
		setSelectedStatus(selectedProfile.getStatus());
        return "/system/car_owner/approve?faces-redirect=true";
    }
	
	public Boolean approvedStatus() {
		return selectedStatus == ProfileStatus.APPROVED;
    }
	
	public Boolean isSelectedProfileApproved() {
		return selectedProfile != null && selectedProfile.getStatus() == ProfileStatus.APPROVED;
    }
	
	public String approveProfile() {
		System.out.println(selectedProfile.toString());
		if(selectedStatus != ProfileStatus.APPROVED) {
			return null;
		}
		try {
			carOwnerProfileService.approveProfile(selectedProfile);
	        setProfiles(carOwnerProfileService.findPendingApproveProfiles());
		} catch (ValidationException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
        return "/system/car_owner/browse?faces-redirect=true";
    }

}
