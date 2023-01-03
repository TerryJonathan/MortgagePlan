package ax.codetest.mortgageplanweb.models;

import ax.codetest.mortgageplanweb.calculation.Calculation;
import ax.codetest.mortgageplanweb.calculation.MonthlyPayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Class defining customers or prospects for the Mortgage Plan Calculator.
 * It also serves as a template for the H2 database schema
 */
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

    /**
     * Constructor for Customers class.
     * This constructor automatically sets the monthly payment, however it
     * could be changed or added to in future versions if needed to some other
     * payment arrangement.
     * @param name of customer
     * @param loan is the total loan amount
     * @param interestRate the interest rate
     * @param numberOfYears the amount of years in which the loan is to be repaid.
     */
    public Customers(String name, double loan, double interestRate, int numberOfYears ){
        this.name= name; this.loan= loan; this.interestRate= interestRate; this.numberOfYears= numberOfYears;
        Calculation calculator= new MonthlyPayment();
        if(loan > 0 && interestRate >= 0 && numberOfYears>0 && name!= ""){
            this.monthlyPayment= calculator.calculatePayment(this);
        }
        else {
            this.name= null; this.loan= 0; this.interestRate= 0; this.numberOfYears= 0;
            System.out.println("Error invalid customer all fields need to be valid");
        }

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
