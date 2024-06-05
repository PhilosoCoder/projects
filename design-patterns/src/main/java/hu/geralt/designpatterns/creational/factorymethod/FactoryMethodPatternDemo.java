package hu.geralt.designpatterns.creational.factorymethod;

// Defines an interface for creating an object, but lets subclasses alter the type of objects that will be created.
// Example: A logistics application where trucks and ships can be created without modifying the client code.

// Defines an interface for creating an object, allowing subclasses to alter the type of objects created.
interface Transport {
    void deliver(); // Method for delivering the transport.
}

// Concrete implementation of Transport for delivering by land.
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by land in a box.");
    }
}

// Concrete implementation of Transport for delivering by sea.
class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by sea in a container.");
    }
}

// Abstract class representing the logistics system.
abstract class Logistics {
    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

// Client Code
public class FactoryMethodPatternDemo {
    public static void main(String[] args) {
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();

        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();
    }
}

