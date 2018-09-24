package com.example.app.process;

import com.example.app.shape.Point;
import com.example.app.shape.Shape;
import com.example.app.shape.ShapeFactory;
import com.example.app.shape.repository.ShapeRepository;

import java.util.Arrays;
import java.util.List;

import static com.example.app.shape.ShapeFactory.*;

public class CommandProcessor {

    private static final List<String> SUPPORTED_SHAPES = Arrays.asList(
            CIRCLE,
            DONUT,
            TRIANGLE
    );

    private static final String HELP_MESSAGE =
            "\n" +
                    "Enter a shape by providing shape type first and then coordinates for shape:\n" +
                    "    circle x y R - creates circle with center at point (x, y) with radius r \n" +
                    "    donut x y R1 R2 - creates donut with center at point (x, y) with inner radius R1 and outer radius R2\n" +
                    "    triangle x1 y1 x2 y2 x3 y3 - creates triangle with vertices (x1, y1) (x2, y2) (x3, y3)\n" +
                    "Exit program:\n" +
                    "    exit\n" +
                    "Help page:\n" +
                    "    help\n";


    private final ShapeFactory shapeFactory;
    private final ShapeRepository shapeRepository;

    public CommandProcessor(ShapeFactory shapeFactory, ShapeRepository shapeRepository) {
        this.shapeFactory = shapeFactory;
        this.shapeRepository = shapeRepository;
    }

    public void processCommand(String input) {

        if (input.trim().equals("help")) {
            showHelp();
        } else if (input.trim().equals("exit")) {
            exit();
        } else if (isCreateShapeCommand(input)) {
            processShapeInput(input);
        } else if (inputHasTwoDecimals(input)) {
            processPointInput(input);
        } else {
            System.out.println("Unknown command " + input);
        }

    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    private void processPointInput(String input) {
        String[] args = input.split(" ");
        Point point = Point.of(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        System.out.println("Checking for shapes with " + point + " inside");

        List<Shape> shapesContainingPoint = shapeRepository.findAllContaining(point);
        shapesContainingPoint.stream()
                .forEach(s -> System.out.println(s + ", has area: " + s.getArea()));

        Double totalArea = shapesContainingPoint.stream()
                .mapToDouble(s -> s.getArea())
                .sum();
        System.out.println("Total area: " + totalArea);
    }

    private void processShapeInput(String input) {
        Shape shape = shapeFactory.createShapeFromCommand(input);
        shapeRepository.saveShape(shape);
        System.out.println("=> " + shape);
    }

    private void showHelp() {
        System.out.println(HELP_MESSAGE);
    }

    private boolean inputHasTwoDecimals(String input) {
        String[] args = input.split(" ");
        if (args.length == 2) {
            try {
                Double.parseDouble(args[0]);
                Double.parseDouble(args[1]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isCreateShapeCommand(String input) {
        return SUPPORTED_SHAPES.stream().anyMatch(s -> input.startsWith(s));
    }
}
