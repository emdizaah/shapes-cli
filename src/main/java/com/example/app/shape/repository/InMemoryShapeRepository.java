package com.example.app.shape.repository;

import com.example.app.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShapeRepository implements ShapeRepository {

    private static final InMemoryShapeRepository INSTANCE = new InMemoryShapeRepository();
    private static final List<Shape> SHAPES = new ArrayList();

    private InMemoryShapeRepository() {}

    public static InMemoryShapeRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public <T extends Shape> void saveShape(T shape) {
        SHAPES.add(shape);
    }

    @Override
    public <T extends Shape> Iterable findAll() {
        return SHAPES;
    }
}
