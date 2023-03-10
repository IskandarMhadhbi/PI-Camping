package esprit.tunisiacamp;

import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TunisiaCampApplication {

    public static void main(String[] args) {
        SpringApplication.run(TunisiaCampApplication.class, args);


    }

}
