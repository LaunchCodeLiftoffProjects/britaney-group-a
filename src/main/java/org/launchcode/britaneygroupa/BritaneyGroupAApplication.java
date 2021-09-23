package org.launchcode.britaneygroupa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BritaneyGroupAApplication
{

	public static void main(String[] args) {
		SpringApplication.run(BritaneyGroupAApplication.class, args);
	}
}

