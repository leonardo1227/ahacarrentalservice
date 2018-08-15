package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.CreditCardInfo;
import edu.mum.cs.cs425.ahacarrentalservice.model.Customer;
import edu.mum.cs.cs425.ahacarrentalservice.model.Rental;
import edu.mum.cs.cs425.ahacarrentalservice.service.RentalService;
import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import edu.mum.cs.cs425.ahacarrentalservice.util.CalcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@Component
@ViewScoped
public class RentController implements IController {

    @Autowired
    private RentalService service;

    private Rental rental;

    private double totalRent;

    private String calcDetails;

    @PostConstruct
    void init() {
        rental = (Rental) getAttributeFromTheSession(Property.SESSION_SELECTED_OFFER);
        rental.setCreditCardInfo(new CreditCardInfo());
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

    public void save(){
        Customer c = new Customer();
        c.setId(1l);
        rental.setCustomer(c);
        rental.getCreditCardInfo().setRental(rental);
        service.save(rental);

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("/system/static/success.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
