package com.example.app.process;

import com.example.app.shape.Circle;
import com.example.app.shape.Donut;
import com.example.app.shape.Triangle;
import com.example.app.shape.repository.ShapeRepository;

public class CreateShapeCommandProcessor {

    private static final String DONUT = "donut";
    private static final String TRIANGLE = "triangle";
    private static final String CIRCLE = "circle";

    private ShapeRepository shapeRepository;

    public CreateShapeCommandProcessor(ShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    public void process(String input) {
        String firstArg = input.split(" ")[0].toLowerCase();

        switch (firstArg) {
            case CIRCLE: {
                Circle circle =  Circle.from(input);
                shapeRepository.saveShape(circle);
                System.out.println(circle);
                break;
            }
            case TRIANGLE: {
                Triangle triangle = Triangle.from(input);
                shapeRepository.saveShape(triangle);
                System.out.println(triangle);
                break;
            }
            case DONUT: {
                Donut donut = Donut.from(input);
                shapeRepository.saveShape(donut);
                System.out.println(donut);
                break;
            }
            default:
                System.out.println("Unknown shape " + firstArg);
        }

    }
}
