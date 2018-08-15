package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.*;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarBrandService;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarProfileService;
import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class CarProfileController implements IController, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarProfileService service;

    @Autowired
    private CarBrandService carBrandService;

    private List<CarProfile> cars;
    private CarProfile carProfile;

    private List<CarBrand> carBrandList;
    private CarBrand carBrandSelected;

    @PostConstruct
    private void init() {
        resetForm();
    }

    public void resetForm() {
        carProfile = new CarProfile();
        carProfile.setModel(new CarModel());
        carBrandSelected = new CarBrand();
        cars = new ArrayList<>();
    }

    public List<CarProfile> getCars() {
        if (cars == null || cars.size() == 0) {
            cars = service.findAll();
        }
        return cars;
    }

    public List<CarBrand> getCarBrandList() {
        if (carBrandList == null || carBrandList.size() == 0) {
            carBrandList = carBrandService.findAll("name");
        }
        return carBrandList;
    }

    public List<Color> getColors() {
        return Arrays.asList(Color.values());
    }

    public void loadCarBrandSelectedObject() {
        if (carBrandSelected != null && carBrandSelected.getId() != null) {
            carBrandSelected = carBrandService.findById(carBrandSelected.getId());
            carProfile.setModel(new CarModel());
        } else {
            carBrandSelected.setId(null);
            carBrandSelected.setModels(new ArrayList<>());
        }
    }


    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void setCarProfile(CarProfile carProfile) {
        this.carProfile = carProfile;
    }

    public CarBrand getCarBrandSelected() {
        return carBrandSelected;
    }

    public void setCarBrandSelected(CarBrand carBrandSelected) {
        this.carBrandSelected = carBrandSelected;
    }

    public void preSave() {
        if (carProfile.getId() == null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            CarOwnerProfile carOwnerProfile = (CarOwnerProfile) session.getAttribute("carOwnerProfileLogged");
            this.carProfile.setCarOwnerProfile(carOwnerProfile);
        }
    }

    public void save() {
        try {
            Boolean isANewOne = getCarProfile().getId() == null;
            service.save(carProfile);
            resetForm();
            if (isANewOne) {
                showMessage("Car Profile Registered Successfully!", null, InformationType.INFORMATION);
            } else {
                showMessage("Car Profile Altered Successfully!", null, InformationType.INFORMATION);
            }
        } catch (ValidationException e) {
            showMessage(e.getMessage(), null, InformationType.ERROR);
        }
    }

    public void select(Long id) {
        carProfile = service.findById(id);
        carBrandSelected = carProfile.getModel().getBrand();
    }

    public void delete(Long id) {
        service.deleteById(id);
        cars = new ArrayList<>();
        showMessage("Car was deleted successfully", null, InformationType.INFORMATION);
    }

    public String managerOffers(CarProfile carProfile) {
        setAttributeInTheSession(Property.SESSION_CARPROFILE_ATTRIBUTE_NAME, carProfile);
        return redirect("/system/car_offer/user_interface");
    }

    public void verifyIfPlateIsAlreadyRegistered(){
        if(service.verifyIfPlateIsAlreadyRegistered(carProfile.getPlate(), carProfile.getId())){
            showMessage("plate", "Plate is already registered in the system", null,InformationType.ERROR);
        }else{
            showMessage("plate", "Valid Plate", null,InformationType.INFORMATION);
        }
    }

}
