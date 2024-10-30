import java.math.BigDecimal;
class Tool {
    private String sToolCode = null;
    private ToolType ttToolType = new ToolType();
    private String sTooltype = null;
    private String sBrand = null;

    Tool(String sToolAbv) {
        sToolCode = sToolAbv;
        if(sToolAbv.equalsIgnoreCase("LADW")){
            sTooltype = "Ladder";
            ttToolType.setDBDailyCharge("1.99");
            ttToolType.setBolWeekendCharge(true);
            sBrand = "Werner";
        }else if(sToolAbv.equalsIgnoreCase("CHNS")){
            sTooltype = "Chainsaw";
            ttToolType.setDBDailyCharge("1.49");
            ttToolType.setHolidayCharge(true);
            sBrand = "Stihl";
        }else  if(sToolAbv.equalsIgnoreCase("JAKD") || sToolAbv.equalsIgnoreCase("JAKR")){
            sTooltype = "Jackhammer";

            ttToolType.setDBDailyCharge("2.99");
            if(sToolAbv.equalsIgnoreCase("JAKD")){
                sBrand = "DeWalt";
            }else{
                sBrand = "Rigid";
            }
        }else{
            sToolCode = "fail";
        }
    }
    public String getSToolCode(){
        return sToolCode;
    }
    public String getSToolType(){
        return sTooltype;
    }
    public String getSBrand(){
        return sBrand;
    }
    public ToolType getTTToolType(){
        return ttToolType;
    }
}
