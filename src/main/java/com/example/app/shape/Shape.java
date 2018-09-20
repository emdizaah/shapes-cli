package com.example.app.shape;

import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;

import java.util.UUID;

public abstract class Shape {

    protected static final String ERROR_ARG_COUNT = "Invalid number of arguments for shape '%s'. Expected %s but got %s";
    protected static final String ERROR_PARSE = "Unable to parse numeric value for %s";

    protected String id;

    public Shape() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    protected static void expectNumberOfArgsEqualTo(String[] args, int expected) {
        int argLength = args.length;

        if (argLength != expected) {
            throw new InvalidNumberOfArgumentsForShapeException(String.format(ERROR_ARG_COUNT, args[0], expected, argLength));
        }
    }

    public abstract Double getArea();
    public abstract boolean isPointInShape(Point point);
}
