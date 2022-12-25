package ax.codetest.mortgageplanweb.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProspectsController {
    // Logger
    private final Logger logger = LoggerFactory.getLogger(ProspectsController.class);
    // Mapping to index in templates on GET request
    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("GET index");

        ModelAndView modelAndView= new ModelAndView("index");
        return modelAndView;
    }

}
