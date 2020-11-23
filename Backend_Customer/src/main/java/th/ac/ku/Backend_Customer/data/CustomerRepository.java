package th.ac.ku.Backend_Customer.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.Backend_Customer.model.Customer;
import th.ac.ku.Backend_Se.model.BankAccount;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByFname (String fname);
    Customer findByCardnumber(String cardNumber);

}
