package ax.codetest.mortgageplanweb.repositories;

import ax.codetest.mortgageplanweb.models.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customers,Long> {
}
