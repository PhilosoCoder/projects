package hu.geralt.designpatterns.behavioral.strategy;

// Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
// It allows the algorithm to vary independently of the client using it.

// Example: Illustrating the Strategy pattern in Java, where different arithmetic operations
// are encapsulated as strategies, allowing clients to choose and switch between them interchangeably.

// Strategy Interface: Declares an interface common to all supported algorithms.
interface Strategy {
    int doOperation(int num1, int num2);
}

// Concrete Strategy: Implements the algorithm using the Strategy interface.
class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

// Concrete Strategy
class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

// Concrete Strategy
class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

// Context: Configured with a Concrete Strategy object and maintains a reference to a Strategy object.
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

// Client Code
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}

