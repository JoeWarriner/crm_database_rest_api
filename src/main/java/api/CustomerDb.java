package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerDb {

    private final HashMap<Long, Customer> customers = new HashMap<>();

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void setupDatabase(){
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS customers( " +
                        "CustomerID SERIAL PRIMARY KEY, " +
                        "CustomerName varchar(255)," +
                        "CustomerOrganisation varchar(255)," +
                        "CustomerPhoneNumber varchar(255)," +
                        "CustomerEmail varchar(255)" +
                        ");"
        );
    }


    public int addCustomer(NewCustomer newcustomer){

        String sqlcommand = "INSERT INTO customers VALUES (DEFAULT, ?, ?, ?, ?) RETURNING CustomerID";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,newcustomer.getName());
            ps.setString(2,newcustomer.getOrganisation());
            ps.setString(3,newcustomer.getPhoneNumber());
            ps.setString(4,newcustomer.getEmail());
            return ps;
        }, keyHolder);

        try {
            return (int) keyHolder.getKey();
        }
        catch (NullPointerException e) {
            return -1;
        }
    }

    public Customer getCustomer(Long id){

        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customers WHERE CustomerID = ?",
                (rs, rowNum) -> {
                    Customer customer = new Customer();
                    customer.setName(rs.getString("CustomerName"));
                    customer.setId(rs.getInt("CustomerID"));
                    customer.setOrganisation(rs.getString("CustomerOrganisation"));
                    customer.setEmail(rs.getString("CustomerEmail"));
                    customer.setPhoneNumber(rs.getString("CustomerPhoneNumber"));
                    return customer;
                },
                id);

        return customers.get(0);
    }

}
