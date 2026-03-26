import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            LOG.severe("Error: File missing in command line arguments");
            LOG.severe("Usage : java -jar drone.jar <file>");
            System.exit(1);
        }

        try (Scanner scanner = new Scanner(new File(args[0]), StandardCharsets.UTF_8)) {
            if(scanner.hasNextLine()) {
                String boardLimits = scanner.nextLine();
                System.out.println(boardLimits);
            }

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                System.out.println(ligne);
            }
        } catch (FileNotFoundException e) {
            LOG.severe("Error: Could not find file " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
