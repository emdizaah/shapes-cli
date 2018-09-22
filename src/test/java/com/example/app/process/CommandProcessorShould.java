package com.example.app.process;

import com.example.app.shape.ShapeFactory;
import com.example.app.shape.repository.ShapeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

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

    /*
    @Mock
    private ShapeRepository shapeRepository;

    private CommandProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new CommandProcessor(shapeRepository);
    }

    @Test
    public void call_ShapeRepository_save_when_valid_circle_is_provided() {


        processor.processCommand("circle 1 2 3");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("circle");
    }

    @Test
    public void call_ShapeRepository_save_when_valid_donut_is_provided() {


        processor.processCommand("donut 4 2 3 5");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("donut");
    }

    @Test
    public void call_ShapeRepository_save_when_valid_triangle_is_provided() {


        processor.processCommand("triangle 0 0 2 2 2 0");

        ArgumentCaptor<Shape> argCaptor = new ArgumentCaptor<>();

        verify(shapeRepository).saveShape(argCaptor.capture());
        //assertThat(argCaptor.getValue().getType()).isEqualTo("triangle");
    }*/

    @Test
    public void call_ShapeCreator_when_first_string_in_input_string_is_circle() {

        String input = "circle 1 2 3";

        processor.processCommand(input);

        verify(shapeFactory).createShapeFromCommand(input);
    }
}