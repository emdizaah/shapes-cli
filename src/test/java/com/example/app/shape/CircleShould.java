package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import org.junit.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class CircleShould {

    @Test
    public void return_circle_with_center_point_x_2_0_y_0_0_and_radius_5_given_input() {
        Point expectedCenterPoint = Point.of(2.0, 0.0);
        Double expectedRadius = 5.0;

        Circle circle = Circle.from("circle 2.0 0.0 5");

        assertThat(circle.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(circle.getRadius()).isEqualTo(expectedRadius);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> Circle.from("circle 5 6 7 9"));

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> Circle.from("circle 5 -9"));

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_non_numeric_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> Circle.from("circle 5 a b "));

        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }

    @Test
    public void return_area_of_4pi_giver_input_circle_0_0_2() {
        Double expectedArea = 4 * PI;
        Shape circle = Circle.from("circle 0 0 2");
        assertThat(circle.getArea()).isEqualTo(expectedArea);
    }

    @Test
    public void return_true_for_Point_0_0_is_in_Circle_0_0_1() {
        Point point = Point.of(0.0, 0.0);
        Shape circle = Circle.from("circle 0 0 1");
        assertThat(circle.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_2_0_is_in_Circle_0_0_1() {
        Point point = Point.of(2.0, 0.0);
        Shape circle = Circle.from("circle 0 0 1");
        assertThat(circle.isPointInShape(point)).isFalse();
    }
}