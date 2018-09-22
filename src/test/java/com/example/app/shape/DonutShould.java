package com.example.app.shape;

import org.junit.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;


public class DonutShould {

    @Test
    public void return_area_of_3pi_given_input_donut_0_0_1_2() {
        Double expectedArea = 3 * PI;
        Donut donut = Donut.of(Point.of(0.0, 0.0), 1.0, 2.0);
        assertThat(donut.getArea()).isEqualTo(expectedArea);
    }

    @Test
    public void return_true_for_Point_0_2_is_in_donut_0_0_1_3() {
        Point point = Point.of(0.0, 2.0);
        Donut donut = Donut.of(Point.of(0.0, 0.0), 1.0, 3.0);
        assertThat(donut.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_0_2_is_in_donut_0_0_3_5() {
        Point point = Point.of(0.0, 2.0);
        Donut donut = Donut.of(Point.of(0.0, 0.0), 3.0, 5.0);
        assertThat(donut.isPointInShape(point)).isFalse();
    }
}