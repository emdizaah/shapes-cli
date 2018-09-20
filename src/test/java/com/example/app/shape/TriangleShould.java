package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import org.junit.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TriangleShould {

    @Test
    public void return_Triangle_with_vertices_at_1_2__5_4__0_0() {
        Point expectedVertexOnePoint = Point.of(1.0,2.0);
        Point expectedVertexTwoPoint = Point.of(5.0,4.0);
        Point expectedVertexThreePoint = Point.of(0.0,0.0);

        Triangle triangle = Triangle.from("triangle 1 2 5 4 0 0");

        assertThat(triangle.getVertexOnePoint()).isEqualTo(expectedVertexOnePoint);
        assertThat(triangle.getVertexTwoPoint()).isEqualTo(expectedVertexTwoPoint);
        assertThat(triangle.getVertexThreePoint()).isEqualTo(expectedVertexThreePoint);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments() {
        Throwable throwable = catchThrowable(() -> {
            Triangle.from("triangle 1 2 3 4 5 6 7");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments() {
        Throwable throwable = catchThrowable(() -> {
            Triangle.from("triangle 1 2 3 4 5");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_too_many_arguments() {
        Throwable throwable = catchThrowable(() -> {
            Triangle.from("triangle 1 2 g 4 1 6");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }

    @Test
    public void return_area_of_2_given_input_triangle_0_0_2_0_0_2() {
        Double expectedArea = 2.0;
        Shape triangle = Triangle.from("triangle 0 0 2 0 0 2");
        assertThat(triangle.getArea()).isEqualTo(expectedArea);
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

    @Test
    public void return_true_for_Point_1_1_is_in_Triangle_0_0_4_0_0_4() {
        Point point = Point.of(1.0, 1.0);
        Shape triangle = Triangle.from("triangle 0 0 4 0 0 4");
        assertThat(triangle.isPointInShape(point)).isTrue();
    }

    @Test
    public void return_false_for_Point_4_4_is_in_Triangle_0_0_4_0_0_4() {
        Point point = Point.of(4.0, 4.0);
        Shape triangle = Triangle.from("triangle 0 0 4 0 0 4");
        assertThat(triangle.isPointInShape(point)).isFalse();
    }
}