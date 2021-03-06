package com.example.app.shape;

import java.util.Objects;

public class Point {

    private Double x;
    private Double y;

    private Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(Double x, Double y) {
        return new Point(x, y);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
