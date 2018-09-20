package com.example.app;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import com.example.app.process.CreateShapeCommandProcessor;
import com.example.app.shape.repository.InMemoryShapeRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

//@SpringBootApplication
public class ShapeApp implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
     //   SpringApplication.run(ShapeApp.class, args);
        new ShapeApp().run(args);
    }

    //@Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        CreateShapeCommandProcessor processor = new CreateShapeCommandProcessor(InMemoryShapeRepository.getInstance());

        while (true) {
            System.out.println("Enter a shape:");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            if (input.equals("help")) {
                System.out.println("this is a help page");
            } else {
                try {
                    processor.process(input);
                } catch (InvalidNumberOfArgumentsForShapeException | InvalidArgumentValueForShapeException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

        InMemoryShapeRepository.getInstance().findAll().forEach(s -> System.out.println(s));

    }

}
