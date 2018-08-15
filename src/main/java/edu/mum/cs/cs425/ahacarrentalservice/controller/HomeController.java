package edu.mum.cs.cs425.ahacarrentalservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import edu.mum.cs.cs425.ahacarrentalservice.util.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.cs.cs425.ahacarrentalservice.model.Offer;
import edu.mum.cs.cs425.ahacarrentalservice.service.OfferService;

@Component
@ViewScoped
public class HomeController implements IController {
	@Autowired
	private OfferService service;

	private List<Offer> offers;

	private Offer selectedOffer;

	private Date currentDate = new Date();

	private int year;


	@PostConstruct
	void init() {
		if(offers == null)
			loadListOffer();
	}

	private List<Offer> loadListOffer() {
		selectedOffer = new Offer();
		return service.filterAvailiableCars();
	}

	public List<Offer> getOffers() {
		return loadListOffer();
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public String select(Long id) {
		selectedOffer = service.findById(id);
		setAttributeInTheSession(Property.SESSION_SELECTED_OFFER, selectedOffer);
		return redirect("/system/rent/rent");
	}

	public void filter(int	brandId, int modelId, int year){
		offers = service.filterOffers(brandId, modelId, year);
	}

	public OfferService getService() {
		return service;
	}

	public void setService(OfferService service) {
		this.service = service;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Offer getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(Offer selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
