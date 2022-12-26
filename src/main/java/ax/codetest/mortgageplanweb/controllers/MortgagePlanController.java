package ax.codetest.mortgageplanweb.controllers;

import ax.codetest.mortgageplanweb.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MortgagePlanController {
    // Logger
    private final Logger logger = LoggerFactory.getLogger(MortgagePlanController.class);
    // Mapping to index in templates on GET request
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("GET index");

        ModelAndView modelAndView= new ModelAndView("index");
        // Adding customers to modelAndView
        // pulling from database and creating a "list object" with the key name of customers
        // this makes it possible to in View template to hav access to this list.
        modelAndView.addObject("customerEntries",customerRepository.findAll());
        return modelAndView;
    }

}
