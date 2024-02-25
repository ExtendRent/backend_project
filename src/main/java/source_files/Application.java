package source_files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import source_files.core.config.SeedDataConfig;
import source_files.utilities.ResponseTimeMeasurement;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("source_files")
public class Application implements CommandLineRunner {
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    @Autowired
    private SeedDataConfig seedDataConfig;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static void processInfo(long duration) {
        System.out.println("process completed in " + duration + " seconds");
    }

    private static void welcome() {
        int additionalSpaces = 62;
        String version = ANSI_BOLD + "(Version 1.0.1)" + ANSI_RESET;
        version = String.format("%" + additionalSpaces + "s", version);
        String start = ANSI_BOLD + "\sApplication started." + ANSI_RESET;

        String asciiText = drawLogo() + ANSI_GREEN + start + ANSI_RESET + version;
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

    private static String drawCar() {

        return
                "__-------__\n" +
                        "      /__---------__ \\\n" +
                        "     / /           \\ \\\n" +
                        "     | |           | |\n" +
                        "     |_|___________|_|\n" +
                        " /-\\|                 |/-\\\n" +
                        "| _ |\\       0       /| _ |\n" +
                        "|(_)| \\      !      / |(_)|\n" +
                        "|___|__\\_____!_____/__|___|\n" +
                        "[_________|PAIR5|_________] \n" +
                        " ||||    ~~~~~~~~     ||||\n" +
                        " `--'                 `--'";
    }

    @Override
    public void run(String... args) throws IOException {
        System.out.println(ANSI_BLUE + ANSI_BOLD + "Application starting..." + ANSI_RESET);
        ResponseTimeMeasurement.start();
        seedDataConfig.runFirst();
        long duration = ResponseTimeMeasurement.measure() / 1000;
        processInfo(duration);
        welcome();
    }
}

