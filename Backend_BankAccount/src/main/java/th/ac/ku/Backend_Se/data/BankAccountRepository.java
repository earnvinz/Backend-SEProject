package th.ac.ku.Backend_Se.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.Backend_Se.model.BankAccount;

import java.util.List;


@Repository
public interface BankAccountRepository extends
        JpaRepository<BankAccount,Integer> {

    List<BankAccount> findByCustomerId(int customerId); // เพิ่มวิธีการคืนค่าเป็น list
    BankAccount findByAccountNumber(String accountNumber);
}
