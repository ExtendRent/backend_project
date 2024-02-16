package source_files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import source_files.core.config.SeedDataConfig;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("source_files")
public class Application implements CommandLineRunner {
    @Autowired
    private SeedDataConfig seedDataConfig;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        seedDataConfig.run();
    }
}
