package source_files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import source_files.core.config.SeedDataConfig;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private SeedDataConfig seedDataConfig;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        seedDataConfig.run();
    }
}
