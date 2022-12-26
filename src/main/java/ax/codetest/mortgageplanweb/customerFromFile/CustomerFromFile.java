package ax.codetest.mortgageplanweb.customerFromFile;

import ax.codetest.mortgageplanweb.models.Customers;
import ax.codetest.mortgageplanweb.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // So springboot recognises it on setup
public class CustomerFromFile implements CommandLineRunner {
    private  final Logger logger= LoggerFactory.getLogger(CustomerFromFile.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        fromFile();
    }

    // Read file and import CustomerProspects from file
    private void fromFile() {
        if(customerRepository.count()== 0){
            CustomerHandler customerHandler = new CustomerHandler();
            customerHandler.populateListFromFile(); // Read file and place in list
            List<Customers> customerList= customerHandler.getCustomersList(); // Get list
            printList(customerList); // Print out list
            customerRepository.saveAll(customerList);// Add to H2 Database
        }
        logger.info("Number of customers: {}", customerRepository.count());
    }

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