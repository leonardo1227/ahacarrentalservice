package edu.mum.cs.cs425.ahacarrentalservice.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private CarBrand brand;

    @OneToMany(mappedBy = "model")
    private List<CarProfile> carProfiles = new ArrayList<>();

    public CarModel() {
    }

    public CarModel(String name, CarBrand brand, List<CarProfile> carProfiles) {
        this.name = name;
        this.brand = brand;
        this.carProfiles = carProfiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public List<CarProfile> getCarProfiles() {
        return carProfiles;
    }

    public void setCarProfiles(List<CarProfile> carProfiles) {
        this.carProfiles = carProfiles;
    }
}
