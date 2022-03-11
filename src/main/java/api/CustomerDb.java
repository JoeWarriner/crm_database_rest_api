package api;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerDb {

    private final HashMap<Long, Customer> customers = new HashMap<>();

    private final AtomicLong counter = new AtomicLong();

    public void addCustomer(Customer customer){
        customers.put(counter.incrementAndGet(), customer);
    }

    public Customer getCustomer(Long id){
        return customers.get(id);
    }

}
