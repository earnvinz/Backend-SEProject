package th.ac.ku.Backend_Customer.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.Backend_Customer.data.CustomerRepository;
import th.ac.ku.Backend_Customer.model.Customer;
import th.ac.ku.Backend_Se.model.BankAccount;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    CustomerRepository repository;

    public CustomerRestController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<Customer> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getOne(@PathVariable  int id){
        try {
            return repository.findById(id).get();
        }
        catch (NoSuchElementException e){
            return null;
        }
    }


    @GetMapping("/customer/{Fname}")
    public List<Customer> getByName(@PathVariable String Fname){
        try {
            return repository.findByFname(Fname);
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    @PostMapping
    public void create(@RequestBody Customer customer){
        repository.save(customer); /* repo.save = sql (insert)*/
    }

    @PutMapping("/{id}")
    public void updatePassword(@PathVariable int id,@RequestBody Customer customer){
        Customer ctm = repository.findById(id).get();
        ctm.setPin(customer.getPin());
        repository.save(ctm);

    }

    @GetMapping("/Customer/cardnumber/{cardnumber}")
    public Customer getBycardNumber(@PathVariable String cardnumber){
        Customer customer = repository.findByCardnumber(cardnumber);
        return customer;
    }

    @DeleteMapping("/Customer/cardnumber/{cardnumber}") // ลบบัญชี
    public void deleteByCardnumber(@PathVariable String cardnumber) { // public Customer
        Customer record = repository.findByCardnumber(cardnumber);
        repository.deleteById(record.getId());
    }
}
