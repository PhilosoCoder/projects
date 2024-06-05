package hu.geralt.designpatterns.structural.facade;

// Provides a unified interface to a set of interfaces in a subsystem, simplifying interactions for the client.
// Example: Providing a simple interface for complex underlying code libraries.

// SubsystemA
class SubsystemA {
    public void operationA() {
        System.out.println("SubsystemA: Performing operation A");
    }
}

// SubsystemB
class SubsystemB {
    public void operationB() {
        System.out.println("SubsystemB: Performing operation B");
    }
}

// Facade
class Facade {
    private final SubsystemA subsystemA;
    private final SubsystemB subsystemB;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
    }

    // Executes a complex operation by coordinating calls to subsystems.
    public void operation() {
        subsystemA.operationA();
        subsystemB.operationB();
    }
}

// Client Code
public class FacadePatternDemo {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}


