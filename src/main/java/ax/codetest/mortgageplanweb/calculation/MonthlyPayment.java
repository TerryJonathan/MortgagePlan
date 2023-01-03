package ax.codetest.mortgageplanweb.calculation;

import ax.codetest.mortgageplanweb.models.Customers;

/**
 * MonthlyPayment clas calculates the monthly payment for a customer
 * based on total loan amount, interest rate and the duration of the loan.
 * <br>
 * It uses the formula E = U[b(1 + b)^p]/[(1 + b)^p - 1]
 * p = Number of payments   |   b = Interest on a monthly basis
 * U = Total loan           |   E = Fixed monthly payment
 * <br>
 * Implements calculatePayment from Calculation
 */
public class MonthlyPayment implements Calculation {
    /**
     * Method that calculates the monthly payment for a customer
     * @param customer a prospect containing loan amount, interest rate and, loan duration
     * @return double value, the monthly payment
     */
    @Override
    public double calculatePayment(Customers customer) {
        int numberOfPayments= customer.getNumberOfYears()*12;
        double monthlyInterest=(customer.getInterestRate()/100)/12;
        //Original formula:  E = U[b(1 + b)^p]/[(1 + b)^p - 1]
        double x= pow(monthlyInterest+1,numberOfPayments); // x = (1+b)^p
        //Simplified formula:  E = U[bx]/[x - 1]
        return customer.getLoan()* (monthlyInterest*x) / (x-1);
    }
    public double pow(double base, int exponent){
        if (exponent<0){
            throw new IllegalArgumentException("Exponent can't be negativ");

        }
        double result=1;
        for (int i = exponent; i !=0 ; i--) {
            result= result * base;
        }
        if(base<0){
            result= result- result*2;
        }
        return result;
    }


}


