package edu.mum.cs.cs425.ahacarrentalservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CAROWNERAPPLICATIONS")
public class CarOwnerProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "*First Name is required")
	private String firstName;

	@NotEmpty(message = "*Last Name is required")
	private String lastName;

	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dob;

	@NotEmpty(message = "*Email Address is required")
	private String emailAddress;

	private String phone;
	private String address;
	
	@OneToOne
	private BankAccount bankAccount;
	
	@OneToMany(mappedBy="carOwnerProfile")
	private List<CarProfile> carProfiles;

	private String status;

	public CarOwnerProfile() {
		super();
	}

	public CarOwnerProfile(Long id, String firstName, String lastName, Date dob, String emailAddress, String phone,
			String address, BankAccount bankAccount, List<CarProfile> carProfiles, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.emailAddress = emailAddress;
		this.phone = phone;
		this.address = address;
		this.bankAccount = bankAccount;
		this.carProfiles = carProfiles;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<CarProfile> getCarProfiles() {
		return carProfiles;
	}

	public void setCarProfiles(List<CarProfile> carProfiles) {
		this.carProfiles = carProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CarOwnerProfile other = (CarOwnerProfile) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null) {
				return false;
			}
		} else if (!emailAddress.equals(other.emailAddress)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (dob == null) {
			if (other.dob != null) {
				return false;
			}
		} else if (!dob.equals(other.dob)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Application [id=%s, firstName=%s, lastName=%s, DOB=%s, emailAddress=%s, Phone=%s, Address=%s, Status=%s]",
				id, firstName, lastName, dob, emailAddress, phone, address, status);
	}

}
