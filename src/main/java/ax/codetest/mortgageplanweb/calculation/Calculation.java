package ax.codetest.mortgageplanweb.calculation;

import ax.codetest.mortgageplanweb.models.Customers;

/**
 * Interface for Calculation incase other methods of payment would be desired
 * it would make process of implementation easier
 */
public interface Calculation {
    double calculatePayment(Customers customer);
}
