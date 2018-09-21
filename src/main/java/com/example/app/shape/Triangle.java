package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;

public class Triangle extends Shape {

    private Point vertexOnePoint;
    private Point vertexTwoPoint;
    private Point vertexThreePoint;

    private Triangle(Point vertexOnePoint, Point vertexTwoPoint, Point vertexThreePoint) {
        this.vertexOnePoint = vertexOnePoint;
        this.vertexTwoPoint = vertexTwoPoint;
        this.vertexThreePoint = vertexThreePoint;
    }

    public static Triangle from(String input) {
        String[] args = input.split(" ");

        validateInputForShape(args, 7);

        try {

            Double vertexOneX = Double.parseDouble(args[1]);
            Double vertexOneY = Double.parseDouble(args[2]);
            Double vertexTwoX = Double.parseDouble(args[3]);
            Double vertexTwoY = Double.parseDouble(args[4]);
            Double vertexThreeX = Double.parseDouble(args[5]);
            Double vertexThreeY = Double.parseDouble(args[6]);

            return new Triangle(
                    Point.of(vertexOneX, vertexOneY),
                    Point.of(vertexTwoX, vertexTwoY),
                    Point.of(vertexThreeX, vertexThreeY)
            );
        } catch(NumberFormatException nfe) {
            throw new InvalidArgumentValueForShapeException(String.format(ERROR_PARSE, args[0]));

        }
    }

    public Point getVertexOnePoint() {
        return vertexOnePoint;
    }

    public Point getVertexTwoPoint() {
        return vertexTwoPoint;
    }

    public Point getVertexThreePoint() {
        return vertexThreePoint;
    }

    @Override
    public Double getArea() {
        Double x1 = vertexOnePoint.getX();
        Double y1 = vertexOnePoint.getY();
        Double x2 = vertexTwoPoint.getX();
        Double y2 = vertexTwoPoint.getY();
        Double x3 = vertexThreePoint.getX();
        Double y3 = vertexThreePoint.getY();

        return getTriangleArea(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean isPointInShape(Point point) {

        Double a1 = getTriangleArea(
                vertexOnePoint.getX(), vertexOnePoint.getY(),
                point.getX(), point.getY(),
                vertexTwoPoint.getX(), vertexTwoPoint.getY()
        );
        Double a2 = getTriangleArea(
                vertexTwoPoint.getX(), vertexTwoPoint.getY(),
                point.getX(), point.getY(),
                vertexThreePoint.getX(), vertexThreePoint.getY()
        );
        Double a3 = getTriangleArea(
                vertexThreePoint.getX(), vertexThreePoint.getY(),
                point.getX(), point.getY(),
                vertexOnePoint.getX(), vertexOnePoint.getY()
        );

        return (a1 + a2 + a3) == getArea();
    }

    @Override
    public String toString() {
        return String.format("shape %s: triangle with vertices at (%s, %s), (%s, %s), (%s, %s)",
                id,
                getVertexOnePoint().getX(), getVertexOnePoint().getY(),
                getVertexTwoPoint().getX(), getVertexTwoPoint().getY(),
                getVertexThreePoint().getX(), getVertexThreePoint().getY()
        );
    }

    private Double getTriangleArea(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3) {
        return Math.abs(0.5*(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)));
    }
}
