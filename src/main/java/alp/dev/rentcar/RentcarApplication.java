package alp.dev.rentcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RentcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentcarApplication.class, args);
    }
}