package api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CustomerDb getCustomerDb(){
        return new CustomerDb();
    }
}
