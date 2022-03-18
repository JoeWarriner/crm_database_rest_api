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
    private CustomerDb customerdb;


    @GetMapping("/customer")
    public Customer getCustomer(@RequestParam String id){
        return customerdb.getCustomer(Long.parseLong(id));
    }

    @PostMapping(
            value = "/customer",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String newCustomer(@RequestBody NewCustomer newcustomer){
        int customerID = customerdb.addCustomer(newcustomer);
        return String.format("%d", customerID);
    }
}
