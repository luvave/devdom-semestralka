package org.devdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    //Main class, that starts spring server, on port 4000 (defined in application.properties)
    //Controllers are inside rest folder
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
