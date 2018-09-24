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

    private static final String FILE_OPTION = "file";
    private static final String USE_DB_OPTION = "use-db";
    private static final String FILL_RANDOM_OPTION = "fill-random";

    public static void main(String[] args) {
        new ShapeApp().run(args);
    }

    public void run(String... args) {

        CommandProcessor processor;

        CommandLine cmd = getCommandLine(args);

        boolean useDatabase = cmd.hasOption(USE_DB_OPTION);
        boolean fileForShapesProvided = cmd.hasOption(FILE_OPTION);
        boolean fillRandomShapesToFile = cmd.hasOption(FILL_RANDOM_OPTION);

        if (fillRandomShapesToFile && !fileForShapesProvided) {
            System.out.println("missing file to write random shapes to");
        }

        if (useDatabase) {
            processor = new CommandProcessor(ShapeFactory.getInstance(), H2ShapeRepository.getInstance());
        } else {
            processor = new CommandProcessor(ShapeFactory.getInstance(), InMemoryShapeRepository.getInstance());
        }

        if (fileForShapesProvided) {
            String fileName = cmd.getOptionValue(FILE_OPTION);
            if (fillRandomShapesToFile) {
                Integer rowCount = Integer.parseInt(cmd.getOptionValue(FILL_RANDOM_OPTION));
                RandomShapeGenerator.writeRandomShapesToFile(rowCount, fileName);
            }
            fillShapesFromFile(processor, fileName);
        }

        fillShapesFromUserInput(processor);
    }

    private CommandLine getCommandLine(String... args) {
        Options options = new Options();

        Option fileOption = Option.builder()
                .longOpt(FILE_OPTION)
                .desc("file containing shape data")
                .hasArg(true)
                .numberOfArgs(1)
                .build();

        Option useDatabaseOption = Option.builder()
                .longOpt(USE_DB_OPTION)
                .desc("use in memory H2 database")
                .hasArg(false)
                .build();

        Option fillRandomShapesOption = Option.builder()
                .longOpt(FILL_RANDOM_OPTION)
                .desc("fill n random shapes to file")
                .hasArg(true)
                .optionalArg(false)
                .numberOfArgs(1)
                .build();

        options.addOption(fileOption);
        options.addOption(useDatabaseOption);
        options.addOption(fillRandomShapesOption);

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args, true);
        } catch (ParseException e) {
            System.out.println("Failed to parse command line options");
            System.out.println(e.getMessage());
        }
        return cmd;
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
