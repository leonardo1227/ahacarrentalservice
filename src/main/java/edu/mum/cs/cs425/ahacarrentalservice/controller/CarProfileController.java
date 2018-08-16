package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.*;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarBrandService;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarModelService;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarProfileService;
import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
    @Autowired
    private CarModelService carModelService;


    private List<CarProfile> carProfiles;
    private CarProfile carProfile;
    private List<CarBrand> carBrandList;


    @PostConstruct
    private void init() {
        resetForm();
    }

    public void resetForm() {
        carProfile = new CarProfile();
        carProfile.setModel(new CarModel());
        carProfile.setCarBrandSelected(new CarBrand());
        carProfiles = new ArrayList<>();
    }

    //GETTERS AND SETTERS
    public List<CarProfile> getCarProfiles() {
        if (carProfiles == null || carProfiles.size() == 0) {
//            carProfiles = service.findAll("id");
            carProfiles = service.findByCarOwnerProfile(getUserLoggedIn().getCarOwnerProfile());
        }
        return carProfiles;
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

    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void setCarProfile(CarProfile carProfile) {
        this.carProfile = carProfile;
    }


    //OPERATIONS
    public void loadCarBrandSelectedInformation(){
        if(carProfile.getCarBrandSelected()!=null && carProfile.getCarBrandSelected().getId()!=null){
            carProfile.setCarBrandSelected(carBrandService.findById(carProfile.getCarBrandSelected().getId()));
            carProfile.setModel(new CarModel());
        }
    }

    public void preSave() {
//        if (carProfile.getId() == null) {
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//            CarOwnerProfile carOwnerProfile = (CarOwnerProfile) session.getAttribute("carOwnerProfileLogged");
//            carProfile.setCarOwnerProfile(carOwnerProfile);
//        }
        carProfile.setCarOwnerProfile(getUserLoggedIn().getCarOwnerProfile());
        if(carProfile.getModel().getId()!=null && carProfile.getModel().getName()==null){
            carProfile.setModel(carModelService.findById(carProfile.getModel().getId()));
        }

        carProfile.setStatus(AnalysisStatus.PENDING);
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
        }
        catch (ValidationException e) {
            showMessage(e.getMessage(), null, InformationType.ERROR);
        }
    }

    public void selectToEdit(Long id) {
        carProfile = service.findById(id);
        carProfile.setCarBrandSelected(carProfile.getModel().getBrand());
    }

    public void delete(Long id) {
        service.deleteById(id);
        carProfiles = new ArrayList<>();
        showMessage("Car was deleted successfully", null, InformationType.INFORMATION);
    }

    public String managerOffers(CarProfile carProfile) {
        setAttributeInTheSession(Property.SESSION_CARPROFILE_ATTRIBUTE_NAME, carProfile);
        return redirect(Property.URL_CAR_OFFER_PAGE);
    }

    public void verifyIfPlateIsAlreadyRegistered() {
        if (service.verifyIfPlateIsAlreadyRegistered(carProfile.getPlate(), carProfile.getId())) {
            showMessage("plate", "Plate already registered", null, InformationType.ERROR);
        } else {
            showMessage("plate", "Valid Plate", null, InformationType.INFORMATION);
        }
    }

}
