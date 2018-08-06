package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarStatus;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CarProfileController implements IController, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarProfileService service;

    private List<CarProfile> cars;

    private CarProfile carProfile;

    @PostConstruct
    private void init() {
    	carProfile = new CarProfile();
    }

    public List<CarProfile> getCars() {
        if (cars == null || cars.size() == 0) {
            cars = service.findAll();
        }
        return cars;
    }

    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void setCarProfile(CarProfile carProfile) {
        this.carProfile = carProfile;
    }

    public void save() {
        System.out.println(carProfile.getStatus());
        String message = new String();
        if(carProfile.getId()==null){
//            carProfile.setStatus(CarStatus.PENDING);
            message="The Car was registered successfully!";
        }else{
            message="The Car was edited successfully!";
        }
        service.save(carProfile);
        carProfile = new CarProfile();
        cars = new ArrayList<>();
        showMessage(message,null, InformationType.INFORMATION);
    }

    public void select(Long id){
        carProfile = service.findById(id);
    }

    public void delete(Long id){
        service.deleteById(id);
        cars = new ArrayList<>();
        showMessage("Car was deleted successfully", null, InformationType.INFORMATION);
    }
}
