package hu.geralt.designpatterns.structural.facade;

//Provides a simplified interface to a complex subsystem.
//Example: Providing a simple interface for complex underlying code libraries.

class SubsystemA {
    public void operationA() {
        // ...
    }
}

class SubsystemB {
    public void operationB() {
        // ...
    }
}

class Facade {
    private SubsystemA subsystemA;
    private SubsystemB subsystemB;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
    }

    public void operation() {
        subsystemA.operationA();
        subsystemB.operationB();
    }
}

