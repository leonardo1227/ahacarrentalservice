package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.Rental;
import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import edu.mum.cs.cs425.ahacarrentalservice.util.CalcUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class RentController implements IController {
    private Rental rental;

    private double totalRent;

    private String calcDetails;

    @PostConstruct
    void init() {
        rental = (Rental) getAttributeFromTheSession(Property.SESSION_SELECTED_OFFER);
        totalRent = CalcUtil.calculateTotalRent(rental);
    }

    public double getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(double totalRent) {
        this.totalRent = totalRent;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public String getCalcDetails() {
        calcDetails = "(" + (rental.getOffer().getPrice() - rental.getOffer().getDiscount()) + " x "
                + CalcUtil.getDifferenceDays(rental.getStartDate(), rental.getEndDate()) + ")";
        return calcDetails;
    }

    public void setCalcDetails(String calcDetails) {
        this.calcDetails = calcDetails;
    }


}
