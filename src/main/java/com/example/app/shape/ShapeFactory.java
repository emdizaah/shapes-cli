package com.example.app.shape;

public class ShapeFactory {

    private static final ShapeFactory INSTANCE = new ShapeFactory();

    public static final String DONUT = "donut";
    public static final String TRIANGLE = "triangle";
    public static final String CIRCLE = "circle";

    private ShapeFactory() {}

    public static ShapeFactory getInstance() {
        return INSTANCE;
    }

    public Shape createShape(String input) {
        Shape shape = null;

        if (input.startsWith(DONUT)) {
            shape = Donut.from(input);
        } else if (input.startsWith(CIRCLE)) {
            shape = Circle.from(input);
        } else if (input.startsWith(TRIANGLE)) {
            shape = Triangle.from(input);
        }
        return shape;
    }
}
