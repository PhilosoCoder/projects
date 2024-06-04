package hu.geralt.designpatterns.structural.decorator;

//Adds responsibilities to objects dynamically.
//Example: Adding scrollbars to a window.

interface Component {
    void operation();
}

class ConcreteComponent implements Component {
    @Override
    public void operation() {
        // ...
    }
}

abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        // Additional behavior
    }
}

