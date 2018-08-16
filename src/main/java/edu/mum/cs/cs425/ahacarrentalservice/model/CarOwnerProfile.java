package edu.mum.cs.cs425.ahacarrentalservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CarOwnerProfile {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "*First Name is required")
	private String firstName;

	@NotEmpty(message = "*Last Name is required")
	private String lastName;

	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dob;

	@NotEmpty(message = "*Email Address is required")
	private String emailAddress;

	private String phone;
	private String address;

	@OneToOne
	private BankAccount bankAccount;

	@OneToMany(mappedBy = "carOwnerProfile")
	private List<CarProfile> carProfiles;

	private ProfileStatus status;

	@OneToOne
	private User user;

	public CarOwnerProfile() {
		super();
	}

	public CarOwnerProfile(@NotEmpty(message = "*First Name is required") String firstName, @NotEmpty(message = "*Last Name is required") String lastName, @NotNull Date dob, @NotEmpty(message = "*Email Address is required") String emailAddress, String phone, String address, BankAccount bankAccount, List<CarProfile> carProfiles, ProfileStatus status, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.emailAddress = emailAddress;
		this.phone = phone;
		this.address = address;
		this.bankAccount = bankAccount;
		this.carProfiles = carProfiles;
		this.status = status;
		this.user = user;
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

	public ProfileStatus getStatus() {
		return status;
	}

	public void setStatus(ProfileStatus status) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
