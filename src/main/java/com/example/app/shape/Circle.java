package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;

import static java.lang.Math.PI;

public class Circle extends Shape {

    private Point centerPoint;
    private Double radius;

    private Circle(Point centerPoint, Double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public static Circle from(String input) {

        String[] args = input.split(" ");
        validateInputForShape(args, 4);
        try {
            Double pointX = Double.parseDouble(args[1]);
            Double pointY = Double.parseDouble(args[2]);
            Double radius = Double.parseDouble(args[3]);
            return new Circle(Point.of(pointX, pointY), radius);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));
        }
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public Double getRadius() {
        return radius;
    }

    @Override
    public Double getArea() {
        return PI * radius * radius;
    }

    @Override
    public boolean isPointInShape(Point point) {
        Double x1 = point.getX();
        Double y1 = point.getY();
        Double x2 = centerPoint.getX();
        Double y2 = centerPoint.getY();
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2)) <= radius;
    }

    @Override
    public String toString() {
        return String.format("shape %s: circle with centre at (%s, %s) and radius %s", id, centerPoint.getX(), centerPoint.getY(), getRadius());
    }
}
