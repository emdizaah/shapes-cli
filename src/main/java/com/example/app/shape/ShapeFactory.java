package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;

import static com.example.app.shape.Shape.ERROR_PARSE;
import static com.example.app.shape.Shape.validateInputForShape;

public class ShapeFactory {

    private static final ShapeFactory INSTANCE = new ShapeFactory();

    public static final String DONUT = "donut";
    public static final String TRIANGLE = "triangle";
    public static final String CIRCLE = "circle";

    private ShapeFactory() {}

    public static ShapeFactory getInstance() {
        return INSTANCE;
    }

    public <T extends Shape> T createShapeFromCommand(String input) {
        Shape shape = null;

        if (input.startsWith(DONUT)) {
            shape = createDonut(input);
        } else if (input.startsWith(CIRCLE)) {
            shape = createCircle(input);
        } else if (input.startsWith(TRIANGLE)) {
            shape = createTriangle(input);
        }
        return (T) shape;
    }

    private Triangle createTriangle(String input) {
        String[] args = input.split(" ");

        validateInputForShape(args, 7);

        try {

            Double vertexOneX = Double.parseDouble(args[1]);
            Double vertexOneY = Double.parseDouble(args[2]);
            Double vertexTwoX = Double.parseDouble(args[3]);
            Double vertexTwoY = Double.parseDouble(args[4]);
            Double vertexThreeX = Double.parseDouble(args[5]);
            Double vertexThreeY = Double.parseDouble(args[6]);

            return Triangle.of(
                    Point.of(vertexOneX, vertexOneY),
                    Point.of(vertexTwoX, vertexTwoY),
                    Point.of(vertexThreeX, vertexThreeY)
            );
        } catch(NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));

        }
    }

    private Circle createCircle(String input) {
        String[] args = input.split(" ");
        validateInputForShape(args, 4);
        try {
            Double pointX = Double.parseDouble(args[1]);
            Double pointY = Double.parseDouble(args[2]);
            Double radius = Double.parseDouble(args[3]);
            return Circle.of(Point.of(pointX, pointY), radius);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));
        }
    }

    private Donut createDonut(String input) {
        String[] args = input.split(" ");

        validateInputForShape(args, 5);
        try {

            Double pointX = Double.parseDouble(args[1]);
            Double pointY = Double.parseDouble(args[2]);
            Double radius1 = Double.parseDouble(args[3]);
            Double radius2 = Double.parseDouble(args[4]);
            if (radius1 < radius2) {
                return Donut.of(Point.of(pointX, pointY), radius1, radius2);
            } else {
                return Donut.of(Point.of(pointX, pointY), radius2, radius1);
            }
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));

        }
    }
}
