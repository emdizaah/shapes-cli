package com.example.app.shape;

import org.junit.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;


public class CircleShould {

    @Test
    public void return_area_of_4pi_giver_input_circle_0_0_2() {
        Double expectedArea = 4 * PI;
        Circle circle = Circle.of(Point.of(0.0, 0.0), 2.0);
        assertThat(circle.getArea()).isEqualTo(expectedArea);
    }

    @Test
    public void return_true_for_Point_0_0_is_in_Circle_0_0_1() {
        Point point = Point.of(0.0, 0.0);
        Circle circle = Circle.of(Point.of(0.0, 0.0), 1.0);
        assertThat(circle.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_2_0_is_in_Circle_0_0_1() {
        Point point = Point.of(2.0, 0.0);
        Circle circle = Circle.of(Point.of(0.0, 0.0), 1.0);
        assertThat(circle.isPointInShape(point)).isFalse();
    }
}