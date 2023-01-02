package ax.codetest.mortgageplanweb.customerFromFile;

import ax.codetest.mortgageplanweb.models.Customers;
import ax.codetest.mortgageplanweb.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * InitializeMortgagePlan is run at initial startup of application.
 * It checks if the repository is empty if it is then a local file is read.
 * A list of customers is created from prospects.txt
 * The list is printed to terminal
 */
@Component // Makes springboot recognises it during setup
public class InitializeMortgagePlan implements CommandLineRunner {
    CustomerHandler customerHandler = new CustomerHandler();
    private  final Logger logger= LoggerFactory.getLogger(InitializeMortgagePlan.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        fromFile();
    }

    /**
     * Read file prospects.txt and get Customers and prospects from file
     */
    private void fromFile() {
        if(customerRepository.count()== 0){
            customerHandler.populateListFromFile(); // Read file and place in list
            customerRepository.saveAll(customerHandler.getCustomersList());// Add to H2 Database
            printList(customerHandler.getCustomersList());
        }
        logger.info("Number of customers: {}", customerRepository.count());
    }

    /**
     * On initial application run print list to terminal
     * @param customerList current list containing customers from prospects.txt
     */
    private void printList(List<Customers> customerList){
        int count= 1;
        for (Customers customer: customerList ) {
            System.out.println("\n*********************************************************" +
                    "*********************************************************");
            System.out.println("  Prospect "+count+": "
                    +customer.getName()+" wants to borrow "
                    +customer.getLoan()+" € for a period of "
                    +customer.getNumberOfYears()+" years and pay "
                    +String.format("%.2f",customer.getMonthlyPayment())+" € each month");
            System.out.println("*********************************************************" +
                    "*********************************************************\n");
            count++;
        }
    }


}
