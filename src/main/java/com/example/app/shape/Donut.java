package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;

import static java.lang.Math.PI;

public class Donut extends Shape {

    private Point centerPoint;
    private Double innerRadius;
    private Double outerRadius;

    public Donut(Point centerPoint, Double innerRadius, Double outerRadius) {
        this.centerPoint = centerPoint;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
    }

    public static Donut from(String input) {

        String[] args = input.split(" ");

        validateInputForShape(args, 5);
        try {

            Double pointX = Double.parseDouble(args[1]);
            Double pointY = Double.parseDouble(args[2]);
            Double radius1 = Double.parseDouble(args[3]);
            Double radius2 = Double.parseDouble(args[4]);
            if (radius1 < radius2) {
                return new Donut(Point.of(pointX, pointY), radius1, radius2);
            } else {
                return new Donut(Point.of(pointX, pointY), radius2, radius1);
            }
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));

        }

    }


    public Point getCenterPoint() {
        return centerPoint;
    }

    public Double getInnerRadius() {

        return innerRadius;
    }

    public Double getOuterRadius() {
        return outerRadius;
    }

    @Override
    public Double getArea() {
        return PI * (outerRadius * outerRadius - innerRadius * innerRadius);
    }

    @Override
    public boolean isPointInShape(Point point) {
        Double x1 = point.getX();
        Double y1 = point.getY();
        Double x2 = centerPoint.getX();
        Double y2 = centerPoint.getY();
        Double distanceFromCenter = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return distanceFromCenter >= innerRadius && distanceFromCenter <= outerRadius;
    }

    @Override
    public String toString() {
        return String.format("shape %s: donut with centre at (%s, %s) with inner radius %s and outer radius %s",
                id,
                centerPoint.getX(),
                centerPoint.getY(),
                getInnerRadius(),
                getOuterRadius()
        );
    }
}
