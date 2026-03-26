package com.sijo.drone;

import com.sijo.drone.domain.Board;
import com.sijo.drone.domain.Drone;
import com.sijo.drone.domain.Moveable;
import com.sijo.drone.parser.InputParser;
import com.sijo.drone.parser.TextParser;

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

        InputParser parser = TextParser.getInstance();

        try (Scanner scanner = new Scanner(new File(args[0]), StandardCharsets.UTF_8)) {
            Board board = null;
            if(scanner.hasNextLine()) {
                board = parser.parseBoard(scanner.nextLine());
            }

            if(board == null) {
                LOG.severe("Could not parse first line into board.");
                System.exit(1);
            }

            while (scanner.hasNextLine()) {
                Moveable moveable = parser.parseMoveable(Drone::new, scanner.nextLine());
                if(scanner.hasNextLine()) {
                    String instructions = scanner.nextLine();
                    moveable.executeInstructions(board, instructions);
                }
                System.out.println(moveable);
            }
        } catch (FileNotFoundException e) {
            LOG.severe("Error: Could not find file " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
