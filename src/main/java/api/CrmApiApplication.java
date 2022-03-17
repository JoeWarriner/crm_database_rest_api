package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CrmApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CrmApiApplication.class, args);

	}

}
