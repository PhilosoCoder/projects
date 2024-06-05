package hu.geralt.designpatterns.structural.bridge;

// Decouples an abstraction from its implementation, allowing them to vary independently.
// Example: Separating the abstraction of a shape from its drawing implementation.

// Implementor interface
interface DrawAPI {
    void drawCircle(int radius, int x, int y);
}

// Concrete Implementor
class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing a red circle with radius " + radius + " at position (" + x + ", " + y + ")");
    }
}

// Abstraction: Defines the interface or abstract class that represents the "control tower".
abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// Refined Abstraction: Extends the abstraction and refines its details.
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}

// Client Code
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        redCircle.draw();
    }
}

