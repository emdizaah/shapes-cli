package com.example.app.shape.repository;

import com.example.app.shape.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class H2ShapeRepository implements ShapeRepository {

    private static final String INSERT_SHAPE = "INSERT INTO SHAPE" + "(ID, TYPE, COORDINATES) values" + "(?,?,?)";
    private static final String FIND_ALL_SHAPES = "SELECT * FROM SHAPE";


    private static final H2ShapeRepository instance = new H2ShapeRepository();

    private H2ShapeRepository() {
        try {
            DataSource.getConnection().createStatement().execute("SELECT 1+1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static H2ShapeRepository getInstance() {
        return instance;
    }

    @Override
    public <T extends Shape> void saveShape(T shape) {

        String coordinates = "";

        if (shape instanceof Circle) {
            coordinates = ""
                    + ((Circle) shape).getCenterPoint().getX() + " "
                    + ((Circle) shape).getCenterPoint().getY() + " "
                    + ((Circle) shape).getRadius();
        } else if (shape instanceof Donut) {
            coordinates = ""
                    + ((Donut) shape).getCenterPoint().getX() + " "
                    + ((Donut) shape).getCenterPoint().getY() + " "
                    + ((Donut) shape).getInnerRadius() + " "
                    + ((Donut) shape).getOuterRadius();
        } else if (shape instanceof Triangle) {
            coordinates = ""
                    + ((Triangle) shape).getVertexOnePoint().getX() + " "
                    + ((Triangle) shape).getVertexOnePoint().getY() + " "
                    + ((Triangle) shape).getVertexTwoPoint().getX() + " "
                    + ((Triangle) shape).getVertexTwoPoint().getY() + " "
                    + ((Triangle) shape).getVertexThreePoint().getX() + " "
                    + ((Triangle) shape).getVertexThreePoint().getY();
        }

        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SHAPE);
            statement.setString(1, shape.getId());
            statement.setString(2, shape.getClass().getSimpleName().toLowerCase());
            statement.setString(3, coordinates);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends Shape> List<T> findAllContaining(Point point) {

        List<Shape> allShapes = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SHAPES);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String type = rs.getString("TYPE");
                String[] coordinates = rs.getString("COORDINATES").split(" ");

                Shape shape = null;
                if (type.equals("circle")) {
                    Double x = Double.parseDouble(coordinates[0]);
                    Double y = Double.parseDouble(coordinates[1]);
                    Double radius = Double.parseDouble(coordinates[2]);

                    shape = Circle.of(Point.of(x, y), radius);
                } else if (type.equals("donut")) {
                    Double x = Double.parseDouble(coordinates[0]);
                    Double y = Double.parseDouble(coordinates[1]);
                    Double innerRadius = Double.parseDouble(coordinates[2]);
                    Double outerRadius = Double.parseDouble(coordinates[3]);

                    shape = Donut.of(Point.of(x, y), innerRadius, outerRadius);
                } else if (type.equals("triangle")) {
                    Double x1 = Double.parseDouble(coordinates[0]);
                    Double y1 = Double.parseDouble(coordinates[1]);
                    Double x2 = Double.parseDouble(coordinates[2]);
                    Double y2 = Double.parseDouble(coordinates[3]);
                    Double x3 = Double.parseDouble(coordinates[4]);
                    Double y3 = Double.parseDouble(coordinates[5]);

                    shape = Triangle.of(Point.of(x1, y1), Point.of(x2, y2), Point.of(x3, y3));
                }

                Optional<Shape> optionalShape = Optional.ofNullable(shape);
                if (optionalShape.isPresent() && optionalShape.get().isPointInShape(point)) {
                    allShapes.add(optionalShape.get());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (List<T>) allShapes;
    }

}
