package ax.codetest.mortgageplanweb.controllers;
import ax.codetest.mortgageplanweb.calculation.Calculation;
import ax.codetest.mortgageplanweb.calculation.MonthlyPayment;
import ax.codetest.mortgageplanweb.models.Customers;
import ax.codetest.mortgageplanweb.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the Controller for the Mortgage Plan Web Application.
 * It handels the POST and GET requests from the web interface
 */
@Controller
public class MortgagePlanController {
    private final Logger logger = LoggerFactory.getLogger(MortgagePlanController.class);
    @Autowired
    private CustomerRepository customerRepository ;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("GET index");
        ModelAndView modelAndView= new ModelAndView("index");
        // Adding customers to modelAndView
        // pulling from database and creating a "list object" with the key name of customers
        // this makes it possible in View template to have access to this list.
        modelAndView.addObject("customerEntries",customerRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/add-customer")
    public String addForm(Customers customer){
        return "add-customer";
    }
    @PostMapping("/add")
    public String addNewCustomer(@Valid @ModelAttribute("customers") Customers customer, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-customer-help";
        }
        if(customer.getLoan()<=0|| customer.getInterestRate()<0 ||customer.getNumberOfYears()>0){
            return "add-customer-help";
        }
        Calculation calculator= new MonthlyPayment();
        customer.setMonthlyPayment(calculator.calculatePayment(customer));
        customerRepository.save(customer);
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") long id){
        Customers customer= customerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Customer id: "+ id +"doesn't exist"));
        customerRepository.delete(customer);
        return "redirect:/";
    }
}
