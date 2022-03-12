package api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {
    private final AtomicLong counter = new AtomicLong();
    private CustomerDb customers = new CustomerDb();


    @GetMapping("/customer")
    public Customer getCustomer(@RequestParam String id){
        return customers.getCustomer(Long.parseLong(id));
    }

    @PostMapping(
            value = "/customer",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void newCustomer(@RequestBody Customer customer){
        customers.addCustomer(counter.incrementAndGet(),customer);
    }
}
