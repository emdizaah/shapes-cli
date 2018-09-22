package com.example.app.shape;

import org.junit.Test;

import java.nio.channels.Pipe;

import static org.assertj.core.api.Assertions.assertThat;

public class TriangleShould {

    @Test
    public void return_area_of_2_given_input_triangle_0_0_2_0_0_2() {
        Double expectedArea = 2.0;
        Triangle triangle = Triangle.of(
                Point.of(0.0, 0.0),
                Point.of(2.0, 0.0),
                Point.of(0.0, 2.0)
        );
        assertThat(triangle.getArea()).isEqualTo(expectedArea);
    }

    @Test
    public void return_true_for_Point_1_1_is_in_Triangle_0_0_4_0_0_4() {
        Point point = Point.of(1.0, 1.0);
        Triangle triangle = Triangle.of(
                Point.of(0.0, 0.0),
                Point.of(4.0, 0.0),
                Point.of(0.0, 4.0)
        );
        assertThat(triangle.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_4_4_is_in_Triangle_0_0_4_0_0_4() {
        Point point = Point.of(4.0, 4.0);
        Triangle triangle = Triangle.of(
                Point.of(0.0, 0.0),
                Point.of(4.0, 0.0),
                Point.of(0.0, 4.0)
        );
        assertThat(triangle.isPointInShape(point)).isFalse();
    }
}