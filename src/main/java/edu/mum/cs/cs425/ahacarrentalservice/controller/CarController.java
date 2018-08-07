package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.Car;
import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CarController implements IController,Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarService service;

    private List<Car> cars;

    private Car car;

    @PostConstruct
    private void init() {
        car = new Car();
    }

    public List<Car> getCars() {
        if (cars == null || cars.size() == 0) {
            cars = service.findAll();
        }
        return cars;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String save() {
        String message = new String();
        if(car.getId()==null){
            message="The Car was registered successfully!";
        }else{
            message="The Car was edited successfully!";
        }
        service.save(car);
        car = new Car();
        cars = new ArrayList<>();
        showMessage(message,null, InformationType.INFORMATION);
        return "carProfile?faces-redirect:true";
    }

    public void select(Long id){
        car = service.findById(id);
    }

    public void delete(Long id){
        service.deleteById(id);
        cars = new ArrayList<>();
        showMessage("Car was deleted successfully", null, InformationType.INFORMATION);
    }
}
