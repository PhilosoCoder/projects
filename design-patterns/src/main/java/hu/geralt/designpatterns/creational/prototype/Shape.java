package hu.geralt.designpatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

// Base prototype.
abstract class Shape {

    int x;
    int y;
    String color;

    // A regular constructor.
    public Shape() {
        // ...
    }

    // The prototype constructor. A fresh object is initialized
    // with values from the existing object.
    public Shape(Shape source) {
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
    }

    // The clone operation returns one of the Shape subclasses.
    public abstract Shape clone();
}

// Concrete prototype. The cloning method creates a new object
// in one go by calling the constructor of the current class and
// passing the current object as the constructor's argument.
// Performing all the actual copying in the constructor helps to
// keep the result consistent: the constructor will not return a
// result until the new object is fully built; thus, no object
// can have a reference to a partially-built clone.
class Rectangle extends Shape {

    int width;

    int height;

    public Rectangle() {
        // Default constructor
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

}

class Circle extends Shape {

    int radius;

    public Circle() {
        // Default constructor
    }

    public Circle(Circle source) {
        super(source);
        this.radius = source.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

}

// Somewhere in the client code.
class Application {

    private List<Shape> shapes = new ArrayList<>();

    public Application() {
        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 10;
        circle.radius = 20;
        shapes.add(circle);

        Circle anotherCircle = (Circle) circle.clone();
        shapes.add(anotherCircle);
        // The `anotherCircle` variable contains an exact copy
        // of the `circle` object.

        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        shapes.add(rectangle);
    }

    public void businessLogic() {
        // Prototype rocks because it lets you produce a copy of
        // an object without knowing anything about its type.
        List<Shape> shapesCopy = new ArrayList<>();

        // For instance, we don't know the exact elements in the
        // shapes array. All we know is that they are all
        // shapes. But thanks to polymorphism, when we call the
        // `clone` method on a shape the program checks its real
        // class and runs the appropriate clone method defined
        // in that class. That's why we get proper clones
        // instead of a set of simple Shape objects.
        for (Shape s : shapes) {
            shapesCopy.add(s.clone());
        }

        // The `shapesCopy` array contains exact copies of the
        // `shape` array's children.
    }

}

