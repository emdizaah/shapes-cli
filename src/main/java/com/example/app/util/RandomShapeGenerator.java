package com.example.app.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomShapeGenerator {


    private static final Double RANGE = 20.0;

    public static String createRandomShapeInput() {
        String shape = null;
        int randomInt = (int) (Math.random() * 3);
        switch (randomInt) {
            case 0:
                shape = createRandomCircleInput(); break;
            case 1:
                shape = createRandomDonutInput(); break;
            case 2:
                shape = createRandomTriangleInput(); break;
        }
        return shape;
    }

    public static void writeRandomShapesToFile(int count, String fileName) {
        List<String> shapes = new ArrayList<>();
        int i = 0;
        while (i < count) {
            shapes.add(createRandomShapeInput());
            i++;
        }
        try {

            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            for(String s : shapes) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Double createRandomDouble() {
        return RANGE * Math.random() - RANGE/2;
    }

    private static Double createPositiveRandomDouble() {
        return RANGE/2 * Math.random();
    }

    private static String createRandomDonutInput() {
        String input = "donut " + Arrays.asList(
                createRandomDouble(),
                createRandomDouble(),
                createPositiveRandomDouble(),
                createPositiveRandomDouble()
        ).stream().map(d -> d.toString()).collect(Collectors.joining(" "));
        return input;
    }

    private static String createRandomCircleInput() {
        String input = "circle " + Arrays.asList(
                createRandomDouble(),
                createRandomDouble(),
                createPositiveRandomDouble()
        ).stream().map(d -> d.toString()).collect(Collectors.joining(" "));
        return input;
    }

    private static String createRandomTriangleInput() {
        String input = "triangle " + Arrays.asList(
                createRandomDouble(),
                createRandomDouble(),
                createRandomDouble(),
                createRandomDouble(),
                createRandomDouble(),
                createRandomDouble()
        ).stream().map(d -> d.toString()).collect(Collectors.joining(" "));
        return input;
    }
}
