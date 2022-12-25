package ax.codetest.mortgageplanweb.CustomerFromFile;

import ax.codetest.mortgageplanweb.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // So springboot recognises it on setup
public class CustomerFromFile implements CommandLineRunner {
    private  final Logger logger= LoggerFactory.getLogger(CustomerFromFile.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

    }


}
