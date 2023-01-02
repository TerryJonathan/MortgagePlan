package ax.codetest.mortgageplanweb.calculation;

import ax.codetest.mortgageplanweb.models.Customers;

public class MonthlyPayment implements Calculation {
    @Override
    public double calculatePayment(Customers customer) {
        // p = Number of payments   |   b = Interest on a monthly basis
        // U = Total loan           |   E = Fixed monthly payment
        // x = (1 + b)^p

        // p = Number of payments
        int numberOfPayments= customer.getNumberOfYears()*12;
        // b = Interest on a monthly basis
        double monthlyInterest=(customer.getInterestRate()/100)/12;
        //Original formula:  E = U[b(1 + b)^p]/[(1 + b)^p - 1]
        double x= pow(monthlyInterest+1,numberOfPayments); // (1+b)^p == x
        //Simplified formula:  E = U[bx]/[x - 1]
        return customer.getLoan()* (monthlyInterest*x) / (x-1);
    }
    private double pow(double base, int power){
        double result=1;
        for (int i = power; i !=0 ; i--) {
            result= result * base;
        }
        return result;
    }


}


