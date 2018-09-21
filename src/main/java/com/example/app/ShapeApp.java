package com.example.app;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import com.example.app.process.CommandProcessor;
import com.example.app.shape.ShapeFactory;
import com.example.app.shape.repository.InMemoryShapeRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

public class ShapeApp implements CommandLineRunner {

    public static void main(String[] args) {
        new ShapeApp().run(args);
    }

    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        CommandProcessor processor = new CommandProcessor(
                ShapeFactory.getInstance(),
                InMemoryShapeRepository.getInstance()
        );

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

}
