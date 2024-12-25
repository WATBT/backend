package alter.alter_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "alter.alter_core.repository")/
public class AlterCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlterCoreApplication.class, args);
	}

}
