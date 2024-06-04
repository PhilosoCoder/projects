package hu.geralt.designpatterns.creational.prototype;

//Creates new objects by copying existing ones without compromising their performance.
//Example: Cloning shapes in a graphic application.

abstract class Shape {
    int x, y;
    String color;

    public Shape() {}

    public Shape(Shape target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();
}

class Rectangle extends Shape {
    int width, height;

    public Rectangle() {}

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

    public Circle() {}

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


