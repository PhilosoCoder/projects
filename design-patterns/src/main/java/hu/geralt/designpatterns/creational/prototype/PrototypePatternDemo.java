package hu.geralt.designpatterns.creational.prototype;

// Creates new objects by copying existing ones without compromising their performance.
// Example: Cloning shapes in a graphic application.

abstract class Shape {
    int x, y; // Position
    String color;

    // Default constructor
    protected Shape() {}

    // Copy constructor
    protected Shape(Shape target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();
}

class Rectangle extends Shape {
    int width;
    int height;

    // Default constructor
    public Rectangle() {}

    // Copy constructor
    public Rectangle(Rectangle target) {
        super(target);
        if (target != null) {
            this.width = target.width;
            this.height = target.height;
        }
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}

class Circle extends Shape {
    int radius;

    // Default constructor
    public Circle() {}

    // Copy constructor
    public Circle(Circle target) {
        super(target);
        if (target != null) {
            this.radius = target.radius;
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }
}

public class PrototypePatternDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.x = 10;
        rectangle.y = 20;
        rectangle.color = "blue";
        rectangle.width = 100;
        rectangle.height = 50;

        Rectangle clonedRectangle = (Rectangle) rectangle.clone();

        System.out.println("Original Rectangle:");
        System.out.println(rectangle);
        System.out.println("Cloned Rectangle:");
        System.out.println(clonedRectangle);

        Circle circle = new Circle();
        circle.x = 30;
        circle.y = 40;
        circle.color = "red";
        circle.radius = 25;

        Circle clonedCircle = (Circle) circle.clone();

        System.out.println("Original Circle:");
        System.out.println(circle);
        System.out.println("Cloned Circle:");
        System.out.println(clonedCircle);
    }
}


