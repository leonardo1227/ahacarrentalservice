package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
import edu.mum.cs.cs425.ahacarrentalservice.util.Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public interface IController {

    default void showMessage(String summary, String detail, InformationType type){
        FacesContext
                .getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(Util.getFacesMessageSeverity(type),
                                summary,
                                detail));
    }

}
