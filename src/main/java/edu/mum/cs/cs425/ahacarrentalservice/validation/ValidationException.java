package edu.mum.cs.cs425.ahacarrentalservice.validation;

public class ValidationException extends Exception {

    public ValidationException(){

    }

    public ValidationException(String msg){
        super(msg);
    }

    public ValidationException(Throwable cause){
        super(cause);
    }

    public ValidationException(String msg, Throwable cause){
        super(msg,cause);
    }




}
