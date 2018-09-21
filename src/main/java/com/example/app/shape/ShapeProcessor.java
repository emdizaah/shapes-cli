package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;

public class ShapeProcessor {

    public static final String DONUT = "donut";
    public static final String TRIANGLE = "triangle";
    public static final String CIRCLE = "circle";

    public Shape processShape(String input) {
        String firstArg = input.split(" ")[0].toLowerCase();
        Shape shape = null;

        switch (firstArg) {
            case CIRCLE: {
                shape = Circle.from(input);
                break;
            }
            case TRIANGLE: {
                shape = Triangle.from(input);
                break;
            }
            case DONUT: {
                shape = Donut.from(input);
                break;
            }
        }
        return shape;
    }
}
