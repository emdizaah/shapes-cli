package com.example.app;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import com.example.app.process.CommandProcessor;
import com.example.app.shape.ShapeFactory;
import com.example.app.shape.repository.InMemoryShapeRepository;
import org.apache.commons.cli.*;
import org.springframework.boot.CommandLineRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ShapeApp implements CommandLineRunner {

    public static void main(String[] args) {
        new ShapeApp().run(args);
    }

    public void run(String... args) {

        CommandProcessor processor = new CommandProcessor(
                ShapeFactory.getInstance(),
                InMemoryShapeRepository.getInstance()
        );

        fillShapesFromFileIfPresent(processor, args);
        fillShapesFromUserInput(processor);
    }

    private void fillShapesFromUserInput(CommandProcessor processor) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Enter a shape or two points");
            String input = scanner.nextLine();
            String firstArg = input.split(" ")[0];
            if (firstArg.equals("exit")) {
                break;
            }
            try {
                processor.processCommand(input);
            } catch (InvalidArgumentValueForShapeException | InvalidNumberOfArgumentsForShapeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void fillShapesFromFileIfPresent(CommandProcessor processor, String[] args) {
        Options options = new Options();
        options.addOption("f", "file", true, "file with shapes");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            parser.parse(options, args);

            String fileName = cmd.getOptionValue("f");
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

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
