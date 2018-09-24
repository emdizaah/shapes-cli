package com.example.app.process;

import com.example.app.shape.*;
import com.example.app.shape.repository.ShapeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandProcessorShould {

    @Mock
    private ShapeFactory shapeFactory;

    @Mock
    private ShapeRepository shapeRepository;

    private CommandProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new CommandProcessor(shapeFactory, shapeRepository);
    }


    @Test
    public void call_ShapeRepository_save_when_valid_circle_is_provided() {

        String input = "circle 1 2 3";

        Circle shape = Circle.of(Point.of(1.0, 2.0), 3.0);
        when(shapeFactory.createShapeFromCommand(input)).thenReturn(shape);

        processor.processCommand(input);

        ArgumentCaptor<? extends Shape> argCaptor = new ArgumentCaptor<>();
        verify(shapeRepository).saveShape(argCaptor.capture());
        assertThat(argCaptor.getValue()).isExactlyInstanceOf(Circle.class);
    }

    @Test
    public void call_ShapeRepository_save_when_valid_donut_is_provided() {

        String input = "donut 4 2 3 5";
        Donut donut = Donut.of(Point.of(4.0, 2.0), 3.0, 5.0);
        when(shapeFactory.createShapeFromCommand(input)).thenReturn(donut);

        processor.processCommand(input);

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        assertThat(argCaptor.getValue()).isExactlyInstanceOf(Donut.class);
    }

    @Test
    public void call_ShapeRepository_save_when_valid_triangle_is_provided() {

        String input = "triangle 0 0 2 2 2 0";
        Triangle triangle = Triangle.of(Point.of(0.0, 0.0), Point.of(2.0, 2.0), Point.of(2.0, 0.0));
        when(shapeFactory.createShapeFromCommand(input)).thenReturn(triangle);

        processor.processCommand(input);

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        assertThat(argCaptor.getValue()).isExactlyInstanceOf(Triangle.class);
    }

}