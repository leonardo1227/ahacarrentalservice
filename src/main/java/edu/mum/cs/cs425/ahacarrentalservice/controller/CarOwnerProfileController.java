package edu.mum.cs.cs425.ahacarrentalservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
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

import edu.mum.cs.cs425.ahacarrentalservice.model.Car;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarOwnerProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.service.ICarOwnerProfileService;

@Component
@SessionScoped
public class CarOwnerProfileController {
	@Autowired
	private ICarOwnerProfileService carOwnerProfileService;

	private List<CarOwnerProfile> profiles;

	// placeholder variable
	private CarOwnerProfile profile;

	public List<CarOwnerProfile> getProfiles() {
		return carOwnerProfileService.findPendingApproveProfiles();
	}

	public void setProfiles(List<CarOwnerProfile> carOwnerProfiles) {
		this.profiles = carOwnerProfiles;
	}

	public CarOwnerProfile getProfile() {
		return profile;
	}

	public void setProfile(CarOwnerProfile carOwnerProfile) {
		this.profile = carOwnerProfile;
	}

	@PostConstruct
	private void postConstruct() {
		
	}
	
	public String createProfile() {
        profile = carOwnerProfileService.create(profile);
        return "browse?faces-redirect=true";
    }
	
	public String selectProfile(CarOwnerProfile cop) {
        profile = cop;
        return "approve";
    }
	
	public String approveProfile(CarOwnerProfile cop) {
		System.out.println(cop.toString());
        if(carOwnerProfileService.approveProfile(cop) == null) {
        	return "";
        };
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
		coa.setStatus("New");
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