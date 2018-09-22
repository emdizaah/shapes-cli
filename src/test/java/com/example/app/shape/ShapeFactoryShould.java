package com.example.app.shape;

import com.example.app.exception.InvalidArgumentValueForShapeException;
import com.example.app.exception.InvalidNumberOfArgumentsForShapeException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ShapeFactoryShould {
    
    //CIRCLE INPUTS

    @Test
    public void return_circle_with_center_point_x_2_0_y_0_0_and_radius_5_given_input() {
        Point expectedCenterPoint = Point.of(2.0, 0.0);
        Double expectedRadius = 5.0;

        Circle circle =  ShapeFactory.getInstance().createShapeFromCommand("circle 2 0 5");

        assertThat(circle.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(circle.getRadius()).isEqualTo(expectedRadius);
    }


    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> ShapeFactory.getInstance().createShapeFromCommand(("circle 5 6 7 9")));

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> ShapeFactory.getInstance().createShapeFromCommand(("circle 5 -9")));

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_non_numeric_arguments_for_Circle() {
        Throwable throwable = catchThrowable(() -> ShapeFactory.getInstance().createShapeFromCommand(("circle 5 a b ")));

        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }
    
    
    //DONUT INPUTS

    @Test
    public void return_Donut_with_center_point_x_3_y_5_inner_radius_1_outer_4() {
        Point expectedCenterPoint = Point.of(3.0, 5.0);
        Double expectedInnerRadius = 1.0;
        Double expectedOuterRadius = 4.0;

        Donut donut = ShapeFactory.getInstance().createShapeFromCommand("donut 3.0 5.0 1 4");

        assertThat(donut.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(donut.getInnerRadius()).isEqualTo(expectedInnerRadius);
        assertThat(donut.getOuterRadius()).isEqualTo(expectedOuterRadius);
    }

    @Test
    public void return_Donut_with_center_point_x_3_y_5_inner_radius_2_outer_5() {
        Point expectedCenterPoint = Point.of(3.0, 5.0);
        Double expectedInnerRadius = 2.0;
        Double expectedOuterRadius = 5.0;

        Donut donut = ShapeFactory.getInstance().createShapeFromCommand("donut 3.0 5.0 5.0 2.0");

        assertThat(donut.getCenterPoint()).isEqualTo(expectedCenterPoint);
        assertThat(donut.getInnerRadius()).isEqualTo(expectedInnerRadius);
        assertThat(donut.getOuterRadius()).isEqualTo(expectedOuterRadius);
    }


    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("donut 5 6 7 9 8 5 6 4 5");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("donut 5 -9");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_non_numeric_arguments_for_Donut() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("donut 5 a b 1");
        });

        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }

    //TRIANGLE INPUTS
    @Test
    public void return_Triangle_with_vertices_at_1_2__5_4__0_0() {
        Point expectedVertexOnePoint = Point.of(1.0,2.0);
        Point expectedVertexTwoPoint = Point.of(5.0,4.0);
        Point expectedVertexThreePoint = Point.of(0.0,0.0);

        Triangle triangle = ShapeFactory.getInstance().createShapeFromCommand("triangle 1 2 5 4 0 0");

        assertThat(triangle.getVertexOnePoint()).isEqualTo(expectedVertexOnePoint);
        assertThat(triangle.getVertexTwoPoint()).isEqualTo(expectedVertexTwoPoint);
        assertThat(triangle.getVertexThreePoint()).isEqualTo(expectedVertexThreePoint);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_too_many_arguments() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("triangle 1 2 3 4 5 6 7");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidNumberOfArgumentsForShapeException_given_less_arguments() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("triangle 1 2 3 4 5");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidNumberOfArgumentsForShapeException.class);
    }

    @Test
    public void throw_InvalidArgumentValueForShapeException_given_too_many_arguments() {
        Throwable throwable = catchThrowable(() -> {
            ShapeFactory.getInstance().createShapeFromCommand("triangle 1 2 g 4 1 6");
        });
        assertThat(throwable).isExactlyInstanceOf(InvalidArgumentValueForShapeException.class);
    }
}