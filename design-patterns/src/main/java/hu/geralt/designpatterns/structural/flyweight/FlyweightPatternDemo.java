package hu.geralt.designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

// The Flyweight pattern optimizes memory usage by sharing common state between objects.
// Example: Multiple Circle objects share intrinsic state (shape)
// while managing extrinsic state (color) individually.

// Flyweight interface
interface Shape {
    //Extrinsic State: Not shared, stored outside the flyweight object and passed to it as needed.
    void draw(String color);
}

// Concrete Flyweight
class Circle implements Shape {
    // Intrinsic State: Shared between objects, stored in the flyweight object.
    private final String shapeType;

    public Circle() {
        this.shapeType = "Circle";
    }

    @Override
    public void draw(String color) {
        System.out.println("Drawing a " + shapeType + " with color " + color);
    }
}

// Flyweight Factory: Manage flyweight objects and ensure the reuse of existing ones.
class ShapeFactory {
    private static final Map<String, Shape> shapes = new HashMap<>();

    public static Shape getCircle() {
        Circle circle = (Circle) shapes.get("Circle");
        if (circle == null) {
            circle = new Circle();
            shapes.put("Circle", circle);
            System.out.println("Creating a new Circle object.");
        }
        return circle;
    }
}

// Client Code
public class FlyweightPatternDemo {
    public static void main(String[] args) {
        Shape circle1 = ShapeFactory.getCircle();
        circle1.draw("Red");

        Shape circle2 = ShapeFactory.getCircle();
        circle2.draw("Green");

        Shape circle3 = ShapeFactory.getCircle();
        circle3.draw("Blue");

        // circle1, circle2, circle3 are the same instance
    }
}

