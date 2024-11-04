//import junit.framework.*;
//import junit.runner.*;

import java.text.ParseException;

//import org.junit.Test;
//import junit.extensions.*;

public class CheckoutTest {
    private static Checkout c;

    @Test
    public static void main(String[] args) throws ParseException{
            args = new String[]{"JAKR","5","101","9/3/15"};
            c = new Checkout();
            c.main(args);
   
            args = new String[]{"LADW","3","10","7/2/20"};
            c = new Checkout();
            c.main(args);
            int iTestDays = c.CountHolidaysToRemove(false);
            System.out.println("Test: Holidays to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 1);
            iTestDays = c.CountWeekendDaysToRemove(3, true);
            System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 0);

            args = new String[]{"CHNS","5","25","7/2/15"};
            c = new Checkout();
            c.main(args);
            iTestDays = c.CountHolidaysToRemove(true);
            System.out.println("Test: Holidays to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 0);
            iTestDays = c.CountWeekendDaysToRemove(5, false);
            System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 2);
            
            args = new String[]{"JAKD","6","0","9/3/15"};
            c = new Checkout();
            c.main(args);
            iTestDays = c.CountHolidaysToRemove(false);
            System.out.println("Test: Holidays to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 1);
            iTestDays = c.CountWeekendDaysToRemove(6, false);
            System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 2);

            args = new String[]{"JAKR","9","0","7/2/15"};
            c = new Checkout();
            c.main(args);
            iTestDays = c.CountHolidaysToRemove(false);
            System.out.println("Test: Holidays to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 1);
            iTestDays = c.CountWeekendDaysToRemove(9, false);
            System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 3);

            args = new String[]{"JAKR","4","50","7/2/20"};
            c = new Checkout();
            c.main(args);
            iTestDays = c.CountHolidaysToRemove(false);
            System.out.println("Test: Holidays to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 1);
            iTestDays = c.CountWeekendDaysToRemove(4, false);
            System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
            assert(iTestDays == 2);
            }
    
}
