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
            boolean bCorrect = false;
   
            args = new String[]{"LADW","3","10","7/2/20"};
            c = new Checkout();
            c.main(args);
            bCorrect = HolidayRemoved(false, 1);
            assert(bCorrect);
            bCorrect = WeekendRemoved(true, 3, 0);
            assert(bCorrect);

            args = new String[]{"CHNS","5","25","7/2/15"};
            c = new Checkout();
            c.main(args);
            bCorrect = HolidayRemoved(true, 0);
            assert(bCorrect);
            bCorrect = WeekendRemoved(false, 5, 2);
            assert(bCorrect);
            
            args = new String[]{"JAKD","6","0","9/3/15"};
            c = new Checkout();
            c.main(args);
            bCorrect = HolidayRemoved(false, 1);
            assert(bCorrect);
            bCorrect = WeekendRemoved(false, 6, 2);
            assert(bCorrect);

            args = new String[]{"JAKR","9","0","7/2/15"};
            c = new Checkout();
            c.main(args);
            bCorrect = HolidayRemoved(false, 1);
            assert(bCorrect);
            bCorrect = WeekendRemoved(false, 9, 3);
            assert(bCorrect);
 
            args = new String[]{"JAKR","4","50","7/2/20"};
            c = new Checkout();
            c.main(args);
            bCorrect = HolidayRemoved(false, 1);
            assert(bCorrect);
            bCorrect = WeekendRemoved(false, 4, 2);
            assert(bCorrect);
            }

            private static boolean HolidayRemoved(boolean bCharged, int iDayValidation){
                boolean bCorrect = false;
                try {
                    int iDaysRemoved = c.CountHolidaysToRemove(bCharged);
                    System.out.println("Test: Holidays to remove count " + Integer.toString(iDaysRemoved));
                    if(iDaysRemoved == iDayValidation){
                        bCorrect = true;
                    }else{
                        bCorrect = false;
                    }
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    bCorrect = false;
                }
                return bCorrect;
            }
            private static boolean WeekendRemoved(boolean bWeekendCharged, int iDaysRented, int iValidation){
                boolean bCorrect = false;
                int iTestDays = c.CountWeekendDaysToRemove(iDaysRented, bWeekendCharged);
                System.out.println("Test: Weekend Days to remove count " + Integer.toString(iTestDays));
                if(iDaysRented == iValidation){
                    bCorrect = true;
                }else{
                    bCorrect = false;
                }
    
                return bCorrect;
            }
    
}
