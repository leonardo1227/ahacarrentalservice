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
			newProfile.setAddress("1000 N 4th St. Fairfield, 52557, IA");
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
	
	public void checkUserId() {
		System.out.println(newProfile.getUserId());
		if(carOwnerProfileService.findByUserId(newProfile.getUserId())) {
			String message = "The user id '" + newProfile.getUserId() + "' is already used by other. Please choose another.";
			showMessage(message, message, InformationType.ERROR);
		}
	}
	
	public String createProfile() {
		System.out.println(newProfile.toString());
		if(carOwnerProfileService.findByUserId(newProfile.getUserId())) {
			String message = "The user id '" + newProfile.getUserId() + "' is already used by other. Please choose another.";
			showMessage(message, message, InformationType.ERROR);
			return null;
		}
		newProfile.setStatus(ProfileStatus.PENDING);
		carOwnerProfileService.create(newProfile);
        newProfile = null;
        setProfiles(carOwnerProfileService.findPendingApproveProfiles());
        return "browse?faces-redirect=true";
    }
	
	public String viewProfile() {
		if(selectedProfile == null || selectedProfile.getId() == null) {
			String message = "Please select a profile";
			showMessage(message, null, InformationType.INFORMATION);
			return null;
		}
		setSelectedStatus(selectedProfile.getStatus());
        return "approve?faces-redirect=true";
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
		selectedProfile.setStatus(selectedStatus);
		carOwnerProfileService.approveProfile(selectedProfile);
        setProfiles(carOwnerProfileService.findPendingApproveProfiles());
        return "browse?faces-redirect=true";
    }
	

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat());
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	@ModelAttribute("dateFormat")
	public String dateFormat() {
		return "MM/dd/yyyy";
	}

	@RequestMapping(value = "/applications/browse", method = RequestMethod.GET)
	public ModelAndView carOwnerApplications() {
		ModelAndView mav = new ModelAndView();
		List<CarOwnerProfile> applications = carOwnerProfileService.findPendingApproveProfiles();
		mav.addObject("applications", applications);
		mav.setViewName("applications/browse");
		return mav;
	}

	@RequestMapping(value = "/applications/new", method = RequestMethod.GET)
	public String carOwnerApplicationView(Model model) {
		CarOwnerProfile coa = new CarOwnerProfile();
		Calendar cal = Calendar.getInstance();
		cal.set(1988, Calendar.JANUARY, 10);

		coa.setFirstName("FirstName");
		coa.setLastName("LastName");
		coa.setDob(cal.getTime());
		coa.setEmailAddress("test@mail.com");
		coa.setPhone("111-111-1111");
		coa.setAddress("1000 N 4th St.");
		coa.setStatus(ProfileStatus.PENDING);
		model.addAttribute("co_application", coa);
		return "applications/new";
	}

	@RequestMapping(value = "/applications/new", method = RequestMethod.POST)
	public String createApplication(@Valid @ModelAttribute("co_application") CarOwnerProfile coa,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			System.out.println(bindingResult.getAllErrors());
			model.addAttribute("co_application", coa);
			return "applications/new";
		}
		coa = carOwnerProfileService.create(coa);
		return "redirect:/applications/browse";
	}

	@RequestMapping(value = "/applications/approve/{id}", method = RequestMethod.GET)
	public String viewApplication(@PathVariable Long id, Model model) {
		CarOwnerProfile coa = carOwnerProfileService.findById(id);
		if (coa != null) {
			model.addAttribute("co_application", coa);
			return "applications/approve";
		}
		return "applications/browse";
	}

	@RequestMapping(value = "/applications/approve", method = RequestMethod.POST)
	public String approveApplication(@Valid @ModelAttribute("co_application") CarOwnerProfile coa,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "applications/approve";
		}
		coa = carOwnerProfileService.approveProfile(coa);
		return "redirect:/applications/browse";
	}
}
