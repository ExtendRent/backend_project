package src;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import src.core.config.SeedDataConfig;
import src.core.utilities.ResponseTimeMeasurement;
import src.core.utilities.aspect.logger.service.ServiceLogger;

import java.io.IOException;

import static src.controller.AnsiColorConstant.*;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("src")
public class Application implements CommandLineRunner {

    @Autowired
    private SeedDataConfig seedDataConfig;
    @Autowired
    private ServiceLogger serviceLogger;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static void processInfo(double duration) {
        System.out.println("process completed in " + duration + " seconds");
    }

    private static void welcome() {
        int additionalSpaces = 62;
        String version = ANSI_BOLD + "(Version 1.0.1)" + ANSI_RESET;
        version = String.format("%" + additionalSpaces + "s", version);
        String start = ANSI_BOLD + ANSI_GREEN + "\sApplication started." + ANSI_RESET;

        String asciiText = drawLogo() + start + ANSI_RESET + version;
        System.out.println(asciiText);
    }

    private static String drawLogo() {
        return "\n" + """
                  ______          _                        _   _____                   _  \s
                 |  ____|        | |                      | | |  __ \\                 | | \s
                 | |__    __  __ | |_    ___   _ __     __| | | |__) |   ___   _ __   | |_\s
                 |  __|   \\ \\/ / | __|  / _ \\ | '_ \\   / _` | |  _  /   / _ \\ | '_ \\  | __|
                 | |____   >  <  | |_  |  __/ | | | | | (_| | | | \\ \\  |  __/ | | | | | |_\s
                 |______| /_/\\_\\  \\__|  \\___| |_| |_|  \\__,_| |_|  \\_\\  \\___| |_| |_|  \\__|
                """;
    }

    @Override
    public void run(String... args) throws IOException {
        serviceLogger.disable();
        System.out.println(ANSI_BOLD + ANSI_BLUE + "Application starting..." + ANSI_RESET);
        ResponseTimeMeasurement.start();
        seedDataConfig.runFirst();
        double duration = ResponseTimeMeasurement.end();
        processInfo(duration);
        welcome();
        serviceLogger.enable();
    }
}

