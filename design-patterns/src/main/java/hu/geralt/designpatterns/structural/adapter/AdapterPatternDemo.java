package hu.geralt.designpatterns.structural.adapter;

// Converts an interface of a class into another interface that a client expects.
// Example: Connecting a card reader to a USB port.

// Target interface: Defines the interface the client uses.
interface Target {
    void request();
}

// Adaptee class: Defines an existing interface that needs adapting.
class Adaptee {
    void specificRequest() {
        System.out.println("Adaptee's specific request");
    }
}

// Adapter class: Allows incompatible interfaces to work together.
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest(); // Delegate to adaptee
    }
}

// Client Code
public class AdapterPatternDemo {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();

        Target adapter = new Adapter(adaptee);

        adapter.request();
    }
}


