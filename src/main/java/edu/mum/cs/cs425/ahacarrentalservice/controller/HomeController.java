package edu.mum.cs.cs425.ahacarrentalservice.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import edu.mum.cs.cs425.ahacarrentalservice.model.Rental;
import edu.mum.cs.cs425.ahacarrentalservice.util.CalcUtil;
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

	private Rental rental;

	private Offer selectedOffer;

	private Date currentDate = new Date();

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private Date startDate;

	private String endDate;

	private int year;


	@PostConstruct
	void init() {
		if(offers == null)
			loadListOffer();
	}

	private List<Offer> loadListOffer() {
		selectedOffer = new Offer();
		rental = new Rental();
		return service.findAllAvailables();
	}

	public List<Offer> getOffers() {
		return loadListOffer();
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public String select(Long id) {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String txStartDate = request.getParameter("form:startDate_input");
		String txEndDate = request.getParameter("form:endDate_input");

		if(txStartDate.equals("") || txEndDate.equals("")){
			aadErrorMessage("You need to inform both end and start date");
			return "";
		}
		Date strDt =  new Date(txStartDate);
		Date endDt = new Date(txEndDate);

		if(CalcUtil.getDifferenceDays(strDt,endDt) < 1){
			aadErrorMessage("The end date must be after the start date");
			return "";
		}

		prepareSessionToRedirect(strDt, endDt, id);

		return redirect("/system/rent/rent");
	}

	private void prepareSessionToRedirect(Date stardDt, Date endDt, long id){
		rental.setStartDate(stardDt);
		rental.setEndDate(endDt);
		Offer o = service.findById(id);
		rental.setOffer(o);
		setAttributeInTheSession(Property.SESSION_SELECTED_OFFER, rental);
	}

	public void aadErrorMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

//	public void filter(int	brandId, int modelId, int year){
//		offers = service.filterOffers(brandId, modelId, year);
//	}
//	public void filter()


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

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
