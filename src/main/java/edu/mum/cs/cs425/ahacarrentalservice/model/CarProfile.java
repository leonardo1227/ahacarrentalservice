package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarProfile {

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
	private CarModel model;

	@Enumerated(EnumType.STRING)
	private Color color;

    private Integer year;

    @Enumerated
    private CarStatus status = CarStatus.PENDING;
    
    @ManyToOne
    private CarOwnerProfile carOwnerProfile;

    private String plate;
    
    @OneToMany(mappedBy = "carProfile")
	private List<Offer> offers = new ArrayList<>();

    public CarProfile() {
    }

	public CarProfile(String plate) {
		this.plate = plate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
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

	public CarOwnerProfile getCarOwnerProfile() {
		return carOwnerProfile;
	}

	public void setCarOwnerProfile(CarOwnerProfile carOwnerProfile) {
		this.carOwnerProfile = carOwnerProfile;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
