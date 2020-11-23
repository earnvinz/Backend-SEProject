package th.ac.ku.Backend_Se.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.Backend_Se.data.BankAccountRepository;
import th.ac.ku.Backend_Se.model.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();

    }

    @DeleteMapping("/{id}") // ลบบัญชี
    public void delete(@PathVariable int id) { // public BankAccount
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
//        return record;
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        try {
            return repository.findById(id).get();
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    @GetMapping("/accountnumber/{accountNumber}")
    public BankAccount getbyaccountnumber(@PathVariable String accountNumber) {
        try {
            return repository.findByAccountNumber(accountNumber);
        }
        catch (NoSuchElementException e){
            return null;
        }
    }
    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }


    @PostMapping
    public void create(@RequestBody BankAccount bankAccount){
        repository.save(bankAccount);
//        return repository.findById(bankAccount.getId()).get();
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,
                              @RequestBody BankAccount bankAccount) {
        try {
            BankAccount record = repository.findById(id).get();

            record.setBalance(bankAccount.getBalance());

            repository.save(record);
            return record;
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    @PutMapping("/accountnumber/{accountNumber}")
    public BankAccount updateBalance(@PathVariable String accountNumber,@RequestBody BankAccount bankAccount){
        try {
            BankAccount record = repository.findByAccountNumber(accountNumber);
            record.setBalance(bankAccount.getBalance());
            repository.save(record);
            return record;
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public void DeleteByCustomerId(@PathVariable int customerId){
        List<BankAccount> bankAccounts = repository.findByCustomerId(customerId);
        repository.deleteAll(bankAccounts);
    }



}
