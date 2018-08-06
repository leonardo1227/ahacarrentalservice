package edu.mum.cs.cs425.ahacarrentalservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;
    
    private double price;
    private String description;
    private double discount;
    
    @OneToOne(mappedBy="offer")
    private CarProfile carProfile;
    
    public Offer() {
    }

    public Offer(Long id, double price, String description, double discount, CarProfile carProfile) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.discount = discount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carProfile == null) ? 0 : carProfile.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (carProfile == null) {
			if (other.carProfile != null)
				return false;
		} else if (!carProfile.equals(other.carProfile))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", price=" + price + ", description=" + description + ", discount=" + discount
				+ ", carProfile=" + carProfile + "]";
	}
}
