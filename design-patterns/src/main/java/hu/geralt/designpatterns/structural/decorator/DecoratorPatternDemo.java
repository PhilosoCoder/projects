package hu.geralt.designpatterns.structural.decorator;

// Adds responsibilities to objects dynamically without changing their implementation.
// Example: Adding scrollbars to a window.

// Component interface: For objects that can have responsibilities added dynamically.
interface Component {
    void operation();
}

// Concrete Component: Represents the core object to which responsibilities can be added.
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent: Core operation");
    }
}

// Decorator: Maintains a reference to a Component object
// and defines an interface that conforms to the Component's interface.
abstract class Decorator implements Component {
    protected Component component;

    protected Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

// Concrete Decorator : Adds additional responsibilities to the Component.
class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorA: Additional behavior");
    }
}

// Client Code
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Component component = new ConcreteDecoratorA(new ConcreteComponent());
        component.operation();
    }
}

