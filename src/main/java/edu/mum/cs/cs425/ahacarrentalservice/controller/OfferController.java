package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.CarProfile;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.model.Offer;
import edu.mum.cs.cs425.ahacarrentalservice.service.OfferService;
import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class OfferController implements IController {

    @Autowired
    private OfferService service;

    private CarProfile carProfile;

    private Offer offer;
    private List<Offer> offers;

    @PostConstruct
    void init() {
        loadCarProfile();
        resetForm();
    }

    private void loadCarProfile() {
        carProfile = (CarProfile) getAttributeFromTheSession(Property.SESSION_CARPROFILE_ATTRIBUTE_NAME);
    }

    public void resetForm() {
        offer = new Offer();
        offers = new ArrayList<>();
    }

    public Offer
    getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Offer> getOffers() {
        if (offers == null || offers.size() == 0) {
            offers = service.findByCarProfile(carProfile);
        }
        return offers;
    }
    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void preSave() {
        if (offer.getId() == null) {
            offer.setCarProfile(carProfile);
        }
    }

    public void save() {
        try {
            Boolean isANewOne = offer.getId() == null;
            service.save(offer);
            resetForm();
            if (isANewOne) {
                showMessage("Offer Registered Successfully!", null, InformationType.INFORMATION);
            } else {
                showMessage("Offer Altered Successfully!", null, InformationType.INFORMATION);
            }
        } catch (ValidationException e) {
            showMessage(e.getMessage(), null, InformationType.ERROR);
        }

    }

    public void selectToEdit(Long id) {
        offer = service.findById(id);
    }

    public void delete(Long id) {
        service.deleteById(id);
        offers = new ArrayList<>();
        showMessage("Offer deleted successfully", null, InformationType.INFORMATION);
    }

    public String backToCarProfilesManagement() {
        removeAttributeFromTheSession(Property.SESSION_CARPROFILE_ATTRIBUTE_NAME);
        return redirect(Property.URL_CAR_PROFILE_PAGE);
    }

    public void changePublicationStatus(Offer offer) {
        Boolean initialValue = offer.getPublicationStatus();
        offer.setPublicationStatus(!offer.getPublicationStatus());
        try {
            service.save(offer);
            offers = new ArrayList<>();
            if (offer.getPublicationStatus()) {
                showMessage("Offer Published successfully!", null, InformationType.INFORMATION);
            } else {
                showMessage("Offer Unpublished successfully!", null, InformationType.INFORMATION);
            }
        } catch (ValidationException e) {
            showMessage(e.getMessage(), null, InformationType.ERROR);
            offer.setPublicationStatus(initialValue);
        }

    }


}
