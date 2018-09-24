## Application commands

This is an interactive console app which accepts user input or input from file to create shapes.
The program supports 3 different commands to create shape. Example input:

    circle 0 0 15         // creates a circle at point (0, 0) and radius 15
    donut 0 0 10 15       // creates a donut at point (0, 0), inner radius 10 and outer radius 15
    triangle 0 0 2 0 0 2  // creates a triangle with verices at (0, 0) (2, 0) (0, 2) 

If user enters two digits which represent a point, program finds all shapes having the point, with area of each shape
and total area of all shapes. Example input:
    
    0 0 // searches for shapes with point (0, 0) inside

To show help page enter:
    
    help

Exit program with:

    exit

     
## Features

Shapes can stored in memory or in database (H2). Use `--use-db` to use database.
Shapes can be read from file. Use `--file filename`
You can also generate a number of random shapes and write to file provided. Use `--fill-random number_of_shapes_to_create`

## Usage

#### Prerequisites
*Maven 3* and *Java 8* 

Building with Maven:
    
    mvn package
    
Running the app:
    
    java -jar target/shapes.jar
    
Testing the app:
    
    mvn test
    

## Examples

```sh
$ java -jar target/shapes.jar --use-db                                  //uses H2 database
```

```sh
$ java -jar target/shapes.jar --file shapes                             //reads shapes from file 'shapes'
```            
```sh
$ java -jar target/shapes.jar --use-db --file shapes --fill-random 1000 //uses H2 database and writes 1000 random shapes to file 'shapes' 
```