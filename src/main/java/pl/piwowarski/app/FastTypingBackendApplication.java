package pl.piwowarski.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FastTypingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastTypingBackendApplication.class, args);
    }

}
