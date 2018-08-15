package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double price;
    private Double discount;
    private LocalDate startDate;
    private LocalDate endDate;
	@ManyToOne
    private CarProfile carProfile;
	private Boolean publicationStatus = false;
    
    public Offer() {
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public CarProfile getCarProfile() {
		return carProfile;
	}

	public void setCarProfile(CarProfile carProfile) {
		this.carProfile = carProfile;
	}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(Boolean publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
