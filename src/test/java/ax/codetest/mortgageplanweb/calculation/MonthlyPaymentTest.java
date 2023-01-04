package ax.codetest.mortgageplanweb.calculation;


import ax.codetest.mortgageplanweb.models.Customers;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MonthlyPaymentTest {

    @Test
    void calculatePaymentTest() {
        Customers customer = new Customers("juha", 1000,5,2);
        double expected=43.8713897340686;
        assertEquals(expected,customer.getMonthlyPayment());

        customer= new Customers("Karvinen",4356.0,1.27	,6);
        expected=62.86631476623255;
        assertEquals(expected,customer.getMonthlyPayment());

        // ERROR-Multiple ways of handling this,
        // but customer is accepted nothing is thrown but calculation is not done
        customer= new Customers("TestPerson", 5 ,0,0);
        System.out.println(customer.getMonthlyPayment());
        expected=0;
        assertEquals(expected,customer.getMonthlyPayment());
    }
    @Test
    void powTest() {
        MonthlyPayment monthlyPayment= new MonthlyPayment();

        double expected=1024; double base =4;int exponent=5;
        double result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);

        expected=1; base =1;exponent=1;
        result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);

        base =300;exponent=0;
        result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);

        expected=0; base =0;exponent=132;
        result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);

        expected=-36; base =-6;exponent=2;
        result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);

        expected=0.1677721600000001; base =0.8;exponent=8;
        result= monthlyPayment.pow(base,exponent);
        assertEquals(expected,result);



        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> monthlyPayment.pow(23,-2));

        String expectedString = "Exponent can't be negativ";
        String resultString = exception.getMessage();

        assertTrue(resultString.contains(expectedString));
    }
}