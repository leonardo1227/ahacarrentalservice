package edu.mum.cs.cs425.ahacarrentalservice.model;

public enum CarStatus {
    PENDING(0),
    APPROVED(1),
    DISAPPROVED(2);
    
    private int val;

    private CarStatus(int value){
        val = value;
    }

    public int getValue(){
        return val;
    }
}
