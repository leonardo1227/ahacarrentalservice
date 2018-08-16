package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.AnalysisStatus;
import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarProfileService;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CarProfileAnalysisController implements IController{

    @Autowired
    private CarProfileService service;

    private List<CarProfile> carProfiles;

    public List<CarProfile> getCarProfiles() {
        if(carProfiles==null || carProfiles.size()==0){
            carProfiles=service.findByStatus(AnalysisStatus.PENDING);
        }
        return carProfiles;
    }


    public void resetForm(){
        carProfiles = new ArrayList<>();
    }

    @PostConstruct
    void init(){
        resetForm();
    }

    public void approve(CarProfile carProfile){
        carProfile.setStatus(AnalysisStatus.APPROVED);
        save(carProfile,"Approved");
    }

    public void reject(CarProfile carProfile){
        carProfile.setStatus(AnalysisStatus.REJECTED);
        save(carProfile,"Rejected");
    }

    public void save(CarProfile carProfile, String action){
        try{
            carProfile = service.save(carProfile);
            resetForm();
            showMessage("Car Profile "+action+" Successfully",null,InformationType.INFORMATION);
        }catch (ValidationException e){
            showMessage(e.getMessage(),null,InformationType.ERROR);
        }
    }
}
