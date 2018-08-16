package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.*;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarOwnerProfileService;
import edu.mum.cs.cs425.ahacarrentalservice.service.UserService;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class CarOwnerApplicationController implements IController {

    @Autowired
    private CarOwnerProfileService service;
    @Autowired
    private UserService userService;

    private CarOwnerProfile carOwnerProfile;

    @PostConstruct
    void init() {
        resetForm();
    }

    public void resetForm() {
        carOwnerProfile = new CarOwnerProfile();
        carOwnerProfile.setUser(new User());
        carOwnerProfile.setBankAccount(new BankAccount());
    }

    public CarOwnerProfile getCarOwnerProfile() {
        return carOwnerProfile;
    }

    public void setCarOwnerProfile(CarOwnerProfile carOwnerProfile) {
        this.carOwnerProfile = carOwnerProfile;
    }

    public void checkUsernameAvailability() {
        if (userService.verifyExistsAnyByUsername(carOwnerProfile.getUser().getUsername())) {
            showMessage("username", "Username already taken!", null, InformationType.WARNING);
        }
    }

    public void save() {
        try {

            String password = new BCryptPasswordEncoder().encode(carOwnerProfile.getUser().getPassword());
            carOwnerProfile.getUser().setPassword(password);
            User user = userService.save(carOwnerProfile.getUser());

            if (user.getId() != null) {
                carOwnerProfile.setBankAccount(null);
                carOwnerProfile.setStatus(ProfileStatus.PENDING);
                carOwnerProfile.setUser(user);
                carOwnerProfile = service.save(carOwnerProfile);
                resetForm();
                showMessage("Application registered successfully!", null, InformationType.INFORMATION);
            }

        } catch (ValidationException e) {
            showMessage(e.getMessage(), null, InformationType.ERROR);
        }
    }
}
