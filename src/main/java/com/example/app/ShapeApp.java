package com.example.app;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import com.example.app.process.CommandProcessor;
import com.example.app.shape.ShapeFactory;
import com.example.app.shape.repository.H2ShapeRepository;
import com.example.app.shape.repository.InMemoryShapeRepository;
import com.example.app.util.RandomShapeGenerator;
import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ShapeApp {

    public static void main(String[] args) {
        new ShapeApp().run(args);
    }

    private CommandLine getCommandLine(String ...args) {
        Options options = new Options();
        options.addOption("f", "file", true, "file containing shape data");
        options.addOption("d", "use-database", false, "use in memory H2 database");
        options.addOption("r", "fill-random", true, "fill n random shapes to file");

        CommandLineParser parser = new DefaultParser();
        CommandProcessor processor = null;

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Failed to parse command line options");
            System.out.println(e.getMessage());
        }
        return cmd;
    }

    public void run(String... args) {

        CommandProcessor processor;

        CommandLine cmd = getCommandLine(args);

        try {
            boolean useDatabase = cmd.hasOption("d");
            boolean fileForShapesProvided = cmd.hasOption("f");
            boolean fillRandomShapesToFile = cmd.hasOption("r");

            if (fillRandomShapesToFile && !fileForShapesProvided) {
                System.out.println("missing file to write random shapes to");
            }

            if (useDatabase) {
                processor = new CommandProcessor(ShapeFactory.getInstance(), H2ShapeRepository.getInstance());
            } else {
                processor = new CommandProcessor(ShapeFactory.getInstance(), InMemoryShapeRepository.getInstance());
            }

            if (fileForShapesProvided) {
                String fileName = cmd.getOptionValue("f");
                if (fillRandomShapesToFile) {
                    Integer rowCount = Integer.parseInt(cmd.getOptionValue("r"));
                    RandomShapeGenerator.writeRandomShapesToFile(rowCount, fileName);
                }
                fillShapesFromFile(processor, fileName);
            }

            fillShapesFromUserInput(processor);

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    private void fillShapesFromUserInput(CommandProcessor processor) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a shape or two points");
            String input = scanner.nextLine();
            try {
                processor.processCommand(input);
            } catch (InvalidArgumentValueForShapeException | InvalidNumberOfArgumentsForShapeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void fillShapesFromFile(CommandProcessor processor, String fileName) {
        try {

            InputStream inputStream = new FileInputStream(fileName);
            Scanner fileScanner = new Scanner(inputStream);

            System.out.println("Reading from file " + fileName);
            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                try {
                    processor.processCommand(input);
                } catch (InvalidArgumentValueForShapeException | InvalidNumberOfArgumentsForShapeException e) {
                    System.out.println("Failed to create shape with input " + input);
                }
            }
            System.out.println("Done creating shapes from file");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
