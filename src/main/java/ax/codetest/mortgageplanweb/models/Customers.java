package ax.codetest.mortgageplanweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name="customer")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double loan;
    @Getter
    @Setter
    private double interestRate;
    @Getter
    @Setter
    private int numberOfYears;
    @Getter
    @Setter
    private double monthlyPayment =0;

    public Customers(){

    }
    public Customers(String name, double loan, double interestRate, int numberOfYears ){
        this.name= name;
        this.loan= loan;
        this.interestRate= interestRate;
        this.numberOfYears= numberOfYears;
        this.monthlyPayment= calculateMonthlyPayment(loan, interestRate, numberOfYears);
    }
    private double pow(double base, int power){
        double result=1;
        for (int i = power; i !=0 ; i--) {
            result= result * base;
        }
        return result;
    }
    public double calculateMonthlyPayment(double loan, double interestRate, int numberOfYears) {
        // p = Number of payments   |   b = Interest on a monthly basis
        // U = Total loan           |   E = Fixed monthly payment
        // x = (1 + b)^p

        // p = Number of payments
        int numberOfPayments= numberOfYears*12;
        // b = Interest on a monthly basis
        double monthlyInterest=(interestRate/100)/12;
        //Original formula:  E = U[b(1 + b)^p]/[(1 + b)^p - 1]
        double x= pow(monthlyInterest+1,numberOfPayments); // (1+b)^p == x

        //Simplified formula:  E = U[bx]/[x - 1]
        return loan* (monthlyInterest*x) / (x-1);
    }


    @Override
    public String toString(){
        return String.format("Customer{id=%d, name='%s', loan=%f," +
                "interestRate=%f,numberOfYears=%d,MonthlyPayment=%f"
                , id, name, loan, interestRate,numberOfYears, monthlyPayment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Double.compare(customers.loan, loan) == 0 && Double.compare(customers.interestRate, interestRate) == 0 && numberOfYears == customers.numberOfYears && Double.compare(customers.monthlyPayment, monthlyPayment) == 0 && name.equals(customers.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, loan, interestRate, numberOfYears, monthlyPayment);
    }
}
