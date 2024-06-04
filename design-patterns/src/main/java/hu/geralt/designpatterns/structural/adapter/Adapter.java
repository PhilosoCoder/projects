package hu.geralt.designpatterns.structural.adapter;

//Converts an interface of a class into another interface that a client expects.
//Example: Connecting a card reader to a USB port.

interface Target {
    void request();
}

class Adaptee {
    void specificRequest() {
        // ...
    }
}

class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

