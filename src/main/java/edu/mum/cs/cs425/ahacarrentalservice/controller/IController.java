package edu.mum.cs.cs425.ahacarrentalservice.controller;

import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;
//import edu.mum.cs.cs425.ahacarrentalservice.security.CustomUserDetails;
import edu.mum.cs.cs425.ahacarrentalservice.security.CustomUserDetails;
import edu.mum.cs.cs425.ahacarrentalservice.util.Util;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public interface IController {

    default void showMessage(String summary, String detail, InformationType type) {
        FacesContext
                .getCurrentInstance()
                .addMessage("controllerMessages",
                        new FacesMessage(Util.getFacesMessageSeverity(type),
                                summary,
                                detail));
    }

    default void showMessage(String fieldId, String summary, String detail, InformationType type) {
        FacesContext
                .getCurrentInstance()
                .addMessage(fieldId,
                        new FacesMessage(Util.getFacesMessageSeverity(type),
                                summary,
                                detail));
    }

    default String redirect(String url) {
        return url + "?faces-redirect=true";
    }

    default Object getAttributeFromTheSession(String attributeName) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(attributeName);
    }

    default void setAttributeInTheSession(String attributeName, Object object) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(attributeName, object);
    }

    default void removeAttributeFromTheSession(String attributeName) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove(attributeName);
    }


    default CustomUserDetails getUserLoggedIn() {
        Object userAuthenticatedObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userAuthenticatedObject instanceof CustomUserDetails) {
            return (CustomUserDetails) userAuthenticatedObject;
        } else {
            return null;
        }
    }

}
