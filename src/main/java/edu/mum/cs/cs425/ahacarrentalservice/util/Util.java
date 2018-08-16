package edu.mum.cs.cs425.ahacarrentalservice.util;


import edu.mum.cs.cs425.ahacarrentalservice.model.InformationType;

import javax.faces.application.FacesMessage;

public class Util {

    public static FacesMessage.Severity getFacesMessageSeverity(InformationType informationType){
        switch (informationType){
            case INFORMATION:return FacesMessage.SEVERITY_INFO;
            case ERROR:return FacesMessage.SEVERITY_ERROR;
            case WARNING:return FacesMessage.SEVERITY_WARN;
            case FATAL:return FacesMessage.SEVERITY_FATAL;
            default:return FacesMessage.SEVERITY_WARN;
        }
    }


}
