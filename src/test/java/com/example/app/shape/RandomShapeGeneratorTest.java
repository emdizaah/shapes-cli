package com.example.app.shape;

import com.example.app.util.RandomShapeGenerator;
import org.junit.Test;

public class RandomShapeGeneratorTest {

    @Test
    public void populateRandomShapes() {
        RandomShapeGenerator.writeRandomShapesToFile(1000, "shapes");
    }
}