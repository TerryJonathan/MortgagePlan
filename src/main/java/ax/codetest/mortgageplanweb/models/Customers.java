package ax.codetest.mortgageplanweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    }

    @Override
    public String toString(){
        return String.format("Customer{id=%d, name='%s', loan=%f," +
                "interestRate=%f,numberOfYears=%d,MonthlyPayment=%f"
                , id, name, loan, interestRate,numberOfYears, monthlyPayment);
    }

}
