package hu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Core {
	public static void main(String[] args) {
		SpringApplication.run(Core.class, args);
	}
}
