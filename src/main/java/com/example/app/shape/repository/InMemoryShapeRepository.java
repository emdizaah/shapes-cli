package com.example.app.shape.repository;

import com.example.app.shape.Point;
import com.example.app.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryShapeRepository implements ShapeRepository {

    private static final InMemoryShapeRepository INSTANCE = new InMemoryShapeRepository();
    private static final List<Shape> SHAPES = new ArrayList();

    private InMemoryShapeRepository() {
        System.out.println("Using in memnory shape repository");
    }

    public static InMemoryShapeRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public <T extends Shape> void saveShape(T shape) {
        SHAPES.add(shape);
    }

    @Override
    public List<Shape> findAllContaining(Point point) {
        return SHAPES.stream()
                .filter(s -> s.isPointInShape(point))
                .collect(Collectors.toList());
    }
}
