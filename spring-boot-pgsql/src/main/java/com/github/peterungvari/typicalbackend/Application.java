package com.github.peterungvari.typicalbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author jupi
 */
@SpringBootApplication
@PropertySource("classpath:sql.properties.xml") // Add properties to Environment (used by PesonDao)
public class Application {
    
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }    

}
