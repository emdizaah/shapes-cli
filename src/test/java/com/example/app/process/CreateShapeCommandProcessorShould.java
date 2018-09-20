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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateShapeCommandProcessorShould {

    @Mock
    private ShapeRepository shapeRepository;

    private CreateShapeCommandProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new CreateShapeCommandProcessor(shapeRepository);
    }

    @Test
    public void call_ShapeRepository_save_when_valid_circle_is_provided() {


        processor.process("circle 1 2 3");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("circle");
    }

    @Test
    public void call_ShapeRepository_save_when_valid_donut_is_provided() {


        processor.process("donut 4 2 3 5");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("donut");
    }

    @Test
    public void call_ShapeRepository_save_when_valid_triangle_is_provided() {


        processor.process("triangle 0 0 2 2 2 0");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("triangle");
    }
}