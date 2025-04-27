package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"security"})
public class HarryPotterAPIApp {

    public static void main(String[] args) {
        SpringApplication.run(HarryPotterAPIApp.class, args);
    }
}
