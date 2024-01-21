package source_files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import source_files.core.config.SeedDataConfig;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private SeedDataConfig seedDataConfig;

    @Override
    public void run(String... args) {
        seedDataConfig.run();
    }
}
