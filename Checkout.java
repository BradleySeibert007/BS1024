import java.text.ParseException;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Checkout
 */
public class Checkout {

    private static LocalDate c;
    private static LocalDate cEnd;
    private static LocalDate cForth;
    private static LocalDate cMemorial;
    private static Tool tTool;
 
   public void main(String[] args) throws ParseException {
        String sToolCode = args[0];
        int iDiscount = Integer.valueOf(args[2]);
        int iDays = Integer.valueOf(args[1]);
        String sDate = args[3];
        if(!(sToolCode.equalsIgnoreCase("fail"))){
            if(0 <= iDiscount && iDiscount < 100){
                if(iDays > 0){
                    SetTool(sToolCode);
                    AgreementPart1Print(args);
                    SetCurrentDate(sDate);
                    SetReturnDate(iDays);
                    AgreementPart2Print();
                    int iChargeDays = iDays;
                    iChargeDays = iChargeDays - CountWeekendDaysToRemove(iDays,tTool.getTTToolType().getBolWeekendCharge());
                    iChargeDays = iChargeDays - CountHolidaysToRemove(tTool.getTTToolType().getHolidayCharge());
                    System.out.println("Charge days: " + Integer.toString(iChargeDays));
                    BigDecimal bdDailyCost = tTool.getTTToolType().getDBDailyCharge();
                    BigDecimal bdPreDiscount = bdDailyCost.multiply(new BigDecimal(iChargeDays));
                    System.out.println("Pre-discount charge: " + bdPreDiscount.toString());
                    System.out.println("Discount percent: " + args[2]);
                    BigDecimal bdPercent = new BigDecimal(iDiscount).divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP);
                    BigDecimal bdDiscount = bdPreDiscount.multiply(bdPercent);
                    BigDecimal bdActualCharge = bdPreDiscount.subtract(bdDiscount);
                    System.out.println("Discount amount: " + bdDiscount.toString());
                    System.out.println("Final charge: " + bdActualCharge.toString());
                }else{
                    System.out.println("Invalid number of days: this should be a whole number 1 or greater");
                }

            }else{
                System.out.println("invalid Discount percent: discount should be between 0 and 100");
            }
        }else{
            System.out.println("Invalid Tool Code: current allowable values are 'JAKR', 'LADW','CHNS', and 'JAKD'");
        }
    }

    private static void AgreementPart1Print(String[] args){
        System.out.println("Tool code: " + tTool.getSToolCode().toString());
        System.out.println("Tool type: " + tTool.getSToolType().toString());
        System.out.println("Tool brand: " + tTool.getSBrand().toString());
        System.out.println("Rental days: " + args[1]);
        System.out.println("Check out date: " + args[3]);
    }

    private void AgreementPart2Print(){
        String sMonth = Integer.toString(cEnd.getMonthValue());
        String sDay = Integer.toString(cEnd.getDayOfMonth());
        int iYear = cEnd.getYear();
        iYear = iYear - 2000;
        String sYear = Integer.toString(iYear);
        String sDueDate = sMonth + "/" + sDay + "/" + sYear;             
        System.out.println("Due date: " + sDueDate);
        System.out.println("Daily rental charge: " + tTool.getTTToolType().getDBDailyCharge());
    }

    public int CountWeekendDaysToRemove(int iDays, boolean bWeekendCharge){
        int iWholeRemainder = 0;
        LocalDate cWeekendDayCount = c;
        int iRemoveDays = 0;

        if(!bWeekendCharge){
            while(iWholeRemainder <= iDays){
                 if(cWeekendDayCount.getDayOfWeek().toString().equalsIgnoreCase("Saturday") || cWeekendDayCount.getDayOfWeek().toString().equalsIgnoreCase("Sunday")){
                    iRemoveDays = iRemoveDays + 1;
                }
                iWholeRemainder = iWholeRemainder + 1;
                cWeekendDayCount = cWeekendDayCount.plusDays(1);
   
            }
        }
        return iRemoveDays;
    }

    public int CountHolidaysToRemove(boolean bHolidayCharge) throws ParseException{
        int iDaysToRemove = 0;
        if(!bHolidayCharge){
            SetHolidays();
            if((c.getMonth().toString().equalsIgnoreCase("July") ||  cEnd.getMonth().toString().equalsIgnoreCase("July") ) && !bHolidayCharge){
            if(!cForth.getDayOfWeek().toString().equalsIgnoreCase("Saturday") ||!cForth.getDayOfWeek().toString().equalsIgnoreCase("Sunday")){
                    }
                    if(c.getDayOfMonth() <= 4 && cEnd.getDayOfMonth() >= 4){
                        iDaysToRemove = 1;
                }else if(cForth.getDayOfWeek().toString().equalsIgnoreCase("Saturday")){
                    if(c.getDayOfMonth() <= 3 && cEnd.getDayOfMonth() >= 3){
                        iDaysToRemove = 1;
                    }
                }else if(cForth.getDayOfWeek().toString().equalsIgnoreCase("Sunday")){
                    if(c.getDayOfMonth() <= 5 && cEnd.getDayOfMonth() >= 5){
                        iDaysToRemove = 1;
                    }               
                }
    
            }else if((c.getMonth().toString().equalsIgnoreCase("September") || cEnd.getMonth().toString().equalsIgnoreCase("September")) && !bHolidayCharge){
                    if(c.getDayOfMonth() <= cMemorial.getDayOfMonth() && cMemorial.getDayOfMonth() <= cEnd.getDayOfMonth()){
                        iDaysToRemove = 1;
                    }
            }
    }
    return iDaysToRemove;
    }

    public void SetCurrentDate(String sDateString){
        int iFirstSlash = Integer.valueOf(sDateString.indexOf("/"));
        if(iFirstSlash == 1){
            sDateString = "0" + sDateString;
        }
        int iSecondSlash = Integer.valueOf(sDateString.lastIndexOf("/"));
        if(iSecondSlash ==4){
            sDateString = sDateString.substring(0, 3) + "0" + sDateString.substring(3,sDateString.length());
        }

        iFirstSlash = sDateString.indexOf("/");
        iSecondSlash = sDateString.lastIndexOf("/");
        int iMonth = Integer.valueOf(sDateString.substring(0, iFirstSlash));
        int iDayOfMonth = Integer.valueOf(sDateString.substring(iFirstSlash +1, iSecondSlash));
        int iYear = Integer.valueOf(sDateString.substring(iSecondSlash + 1, sDateString.length()));
        c = LocalDate.of(2000 + iYear, iMonth,iDayOfMonth);
    }

    public void SetReturnDate(int iDays){
        cEnd = c.plusDays(iDays);
    }

    public static void SetHolidays(){
        cForth = LocalDate.of(c.getYear(),7, 4);
        cMemorial = LocalDate.of(c.getYear(),9, 1);
        while(!cMemorial.getDayOfWeek().toString().equalsIgnoreCase("Monday")){
            cMemorial = cMemorial.plusDays(1);
            }
    }
    public void SetTool(String sToolString){
        tTool = new Tool(sToolString);
    }

    public Tool GetTool(){
        return tTool;
    }
}
