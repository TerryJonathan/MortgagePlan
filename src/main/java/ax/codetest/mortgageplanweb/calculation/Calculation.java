package ax.codetest.mortgageplanweb.calculation;

import ax.codetest.mortgageplanweb.models.Customers;

public interface Calculation {
    double calculatePayment(Customers customer);
}
