package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class CarProfile {

    @Id
    @GeneratedValue
    private Long id;
    
    private String model;
    private String brand;
    private String color;
    private Integer year;
    private CarStatus status = CarStatus.PENDING;
    
    @OneToMany(mappedBy = "carProfile")
    private List<Offer> offers;

    public CarProfile() {
    }

	public CarProfile(String model, String brand, String color, Integer year, CarStatus status, List<Offer> offers) {
		this.model = model;
		this.brand = brand;
		this.color = color;
		this.year = year;
		this.status = status;
		this.offers = offers;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


}
