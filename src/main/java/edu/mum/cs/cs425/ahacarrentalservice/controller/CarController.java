package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.Car;
import edu.mum.cs.cs425.ahacarrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
public class CarController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarService service;

    private List<Car> cars;

    @PostConstruct
    private void init() {

        /*service.save(new Car("Fusion","Ford","Silver",2012));
        service.save(new Car("Focus","Ford","Blue",2012));
        service.save(new Car("328i","BMW","Black",2012));
        service.save(new Car("428i","BMW","Black",2015));
        service.save(new Car("Corolla","Toyota","Black",2015));
*/

        cars = service.findAll();
    }

    public List<Car> getCars() {
        return cars;
    }
}
