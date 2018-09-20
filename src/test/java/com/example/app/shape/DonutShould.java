package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import org.junit.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class DonutShould {

    @Test
    public void return_Donut_with_center_point_x_3_y_5_inner_radius_1_outer_4() {
        Point expectedCenterPoint = Point.of(3.0, 5.0);
        Double expectedInnerRadius = 1.0;
        Double expectedOuterRadius = 4.0;

        Donut donut = Donut.from("donut 3.0 5.0 1 4");

        assertThat(donut.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(donut.getInnerRadius()).isEqualTo(expectedInnerRadius);
        assertThat(donut.getOuterRadius()).isEqualTo(expectedOuterRadius);
    }

    @Test
    public void return_Donut_with_center_point_x_3_y_5_inner_radius_2_outer_5() {
        Point expectedCenterPoint = Point.of(3.0, 5.0);
        Double expectedInnerRadius = 2.0;
        Double expectedOuterRadius = 5.0;

        Donut donut = Donut.from("donut 3.0 5.0 5.0 2.0");

        assertThat(donut.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(donut.getInnerRadius()).isEqualTo(expectedInnerRadius);
        assertThat(donut.getOuterRadius()).isEqualTo(expectedOuterRadius);
    }


    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            Donut.from("donut 5 6 7 9 8 5 6 4 5");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            Donut.from("donut 5 -9");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_non_numeric_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            Donut.from("donut 5 a b 1");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }

    @Test
    public void return_area_of_3pi_given_input_donut_0_0_1_2() {
        Double expectedArea = 3 * PI;
        Shape donut = Donut.from("donut 0 0 1 2");
        assertThat(donut.getArea()).isEqualTo(expectedArea);
    }

    @Test
    public void return_true_for_Point_0_2_is_in_donut_0_0_1_3() {
        Point point = Point.of(0.0, 2.0);
        Shape donut = Donut.from("donut 0 0 1 3");
        assertThat(donut.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_0_2_is_in_donut_0_0_3_5() {
        Point point = Point.of(0.0, 2.0);
        Shape donut = Donut.from("donut 0 0 3 5");
        assertThat(donut.isPointInShape(point)).isFalse();
    }
}