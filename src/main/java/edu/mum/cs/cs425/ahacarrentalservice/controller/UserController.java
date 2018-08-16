package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.security.CustomUserDetails;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class UserController implements IController, Serializable {

    public CustomUserDetails getUserLogged() {
        return getUserLoggedIn();
    }
}
