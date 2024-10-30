import junit.framework.*;
import junit.runner.*;

import java.text.ParseException;

import org.junit.Test;
import junit.extensions.*;

public class CheckoutTest {
    private static Checkout c;
    private String [] args;
    @Test
public void TestCheckoutDocumentation() throws ParseException{
    args = new String[]{"JAKR","5","101","9/3/15"};
    c = new Checkout();
    c.main(args);

    }
    @Test
    public static void main(String[] args) throws ParseException{
            args = new String[]{"JAKR","5","101","9/3/15"};
            c = new Checkout();
            c.main(args);
  
            args = new String[]{"LADW","3","10","7/2/20"};
            c = new Checkout();
            c.main(args);
 
            args = new String[]{"CHNS","5","25","7/2/15"};
            c = new Checkout();
            c.main(args);
 
            args = new String[]{"JAKD","6","0","9/3/15"};
            c = new Checkout();
            c.main(args);
 
            args = new String[]{"JAKR","9","0","7/2/15"};
            c = new Checkout();
            c.main(args);

            args = new String[]{"JAKR","4","50","7/2/20"};
            c = new Checkout();
            c.main(args);
 
            }
    
}
