package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class CustomerController {

    @Autowired
    private CustomerDb customers;


    @GetMapping("/customer")
    public Customer getCustomer(@RequestParam String id){
        return customers.getCustomer(Long.parseLong(id));
    }
    // Need to update this so that it works with customer/id

    @PostMapping(
            value = "/customer",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String newCustomer(@RequestBody Customer customer){
        int customerID = customers.addCustomer(customer);
        return String.format("Customer ID: %d", customerID);
    }
}
