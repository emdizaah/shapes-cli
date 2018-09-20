package com.example.app.shape.repository;

import com.example.app.shape.Shape;

public interface ShapeRepository {
    <T extends Shape> void saveShape(T shape);
    <T extends Shape> Iterable findAll();
}
