package source_files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TobetoRentAcarCrewProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TobetoRentAcarCrewProjectApplication.class, args);
	}

}
