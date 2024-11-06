import java.math.BigDecimal;
class ToolType {
    private Boolean bolWeekdayCharge = true;
    private Boolean bolWeekendCharge = false;
    private Boolean bolHolidayCharge = false;
    private BigDecimal bdDailyCharge;
    void constructor(String sType) {

    }
    public void setBolWeekendCharge(Boolean bolUpdate){
        bolWeekendCharge = bolUpdate;
    }
    public Boolean getBolWeekendCharge(){
        return bolWeekendCharge;
    }
    public void setHolidayCharge(Boolean bolUpdate){
        bolHolidayCharge = bolUpdate;
    }
    public Boolean getHolidayCharge(){
        return bolHolidayCharge;
    }
    public void setBolWeekdayCharge(Boolean bolUpdate){
        bolHolidayCharge = bolUpdate;
    }
    public Boolean getBolWeekdayCharge(){
        return bolHolidayCharge;
    }
    public void setDBDailyCharge(String sCost){
        bdDailyCharge = new BigDecimal(sCost);
    }
    public BigDecimal getDBDailyCharge(){
        return bdDailyCharge;
    }
}
