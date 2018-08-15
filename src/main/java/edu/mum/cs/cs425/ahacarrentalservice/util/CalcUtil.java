package edu.mum.cs.cs425.ahacarrentalservice.util;
import edu.mum.cs.cs425.ahacarrentalservice.model.Rental;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalcUtil {
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static double calculateTotalRent(Rental rent){
        return (rent.getOffer().getPrice() -  rent.getOffer().getDiscount()) * getDifferenceDays(rent.getStartDate(), rent.getEndDate());
    }

}
