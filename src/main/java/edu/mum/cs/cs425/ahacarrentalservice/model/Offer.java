package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;
    
    private double price;
    private String description;
    private double discount;

    @Enumerated
	private CarStatus status = CarStatus.PENDING;
    
	@ManyToOne
    private CarProfile carProfile;
    
    public Offer() {
    }

	public Offer(double price, String description, double discount, CarStatus status, CarProfile carProfile) {
		this.price = price;
		this.description = description;
		this.discount = discount;
		this.status = status;
		this.carProfile = carProfile;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

	public CarProfile getCarProfile() {
		return carProfile;
	}

	public void setCarProfile(CarProfile carProfile) {
		this.carProfile = carProfile;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}
}
