package com.example.app.shape.repository;

import com.example.app.shape.Point;
import com.example.app.shape.Shape;

import java.util.List;

public interface ShapeRepository {
    <T extends Shape> void saveShape(T shape);
    <T extends Shape> List<T> findAllContaining(Point point);
}
