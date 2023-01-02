package ax.codetest.mortgageplanweb.customerFromFile;

import ax.codetest.mortgageplanweb.models.Customers;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CustomerHandlerTest {
        private final List<Customers> expected= new ArrayList<>();

        private final CustomerHandler customerHandler= new CustomerHandler();
    @Test
    void readFileTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String expected = """
                Juha,1000,5,2
                Karvinen,4356,1.27,6
                Claes Månsson,1300.55,8.67,2
                "Clarencé,Andersson",2000,6,4
                """;
       // Getting private method readFile()
        Method readFile= CustomerHandler.class.getDeclaredMethod("readFile");
        readFile.setAccessible(true);
        //CustomerHandler customerHandler= new CustomerHandler();
        String result= (String) readFile.invoke(customerHandler); // Running method
        assertEquals(expected,result);
    }
    @Test
    void customerListFromStringTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        expected.add(new Customers("Juha",1000,5,2));
        expected.add(new Customers("Karvinen",4356,1.27,6));
        expected.add(new Customers("Claes Månsson",1300.55,8.67,2));
        expected.add(new Customers("Clarencé Andersson",2000,6,4));

        String inputString = """
                Juha,1000,5,2
                Karvinen,4356,1.27,6
                Claes *************Mån****sson,1300.55,8.67,2
                "Clarencé,Andersson",2000,6,4
                """;

        Method method= CustomerHandler.class.getDeclaredMethod("customersListFromFile",String.class);
        method.setAccessible(true);
        // CustomerHandler customerHandler= new CustomerHandler();
        List<Customers> result= (List<Customers>) method.invoke(customerHandler,inputString);

        for (int i =0 ;i<expected.size();i++){
           assertEquals(expected.get(i), result.get(i));
       }
    }

}