package com.example.app.shape;

import static java.lang.Math.PI;

public class Donut extends Shape {

    private Point centerPoint;
    private Double innerRadius;
    private Double outerRadius;

    private Donut(Point centerPoint, Double innerRadius, Double outerRadius) {
        this.centerPoint = centerPoint;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
    }


    public static Donut of(Point center, Double innerRadius, Double outerRadius) {
        return new Donut(center, innerRadius, outerRadius);
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
                innerRadius,
                outerRadius
        );
    }
}
