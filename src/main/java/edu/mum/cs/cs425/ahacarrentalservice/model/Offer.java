package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;
    
    private double price;
    private String description;
    private double discount;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated
	private AnalysisStatus status = AnalysisStatus.PENDING;
    
	@ManyToOne
    private CarProfile carProfile;
    
    public Offer() {
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

	public AnalysisStatus getStatus() {
		return status;
	}

	public void setStatus(AnalysisStatus status) {
		this.status = status;
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
}
