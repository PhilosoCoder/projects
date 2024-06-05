package hu.geralt.designpatterns.behavioral.observer;

// Establishes a one-to-many dependency between objects,
// where changes in one object trigger updates in all its dependents automatically.
// Example: A subject maintains a list of observers and notifies them of state changes.

import java.util.ArrayList;
import java.util.List;

// Observer interface for updating observers.
interface Observer {
    void update(String state);
}

// Subject class maintaining observers and notifying them of state changes.
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    // Sets the state and notifies observers.
    public void setState(String state) {
        this.state = state;
        notifyAllObservers();
    }

    // Gets the state.
    public String getState() {
        return state;
    }

    // Attaches an observer to the subject.
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notifies all observers of state changes.
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}

// Concrete observer implementing the update method.
class ConcreteObserver implements Observer {
    private String name;

    // Initializes the observer with a name.
    public ConcreteObserver(String name) {
        this.name = name;
    }

    // Updates the observer with the new state.
    @Override
    public void update(String state) {
        System.out.println("Observer " + name + " updated with state: " + state);
    }
}

// Client Code
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        // Attaches observers to the subject.
        subject.addObserver(new ConcreteObserver("Observer1"));
        subject.addObserver(new ConcreteObserver("Observer2"));

        // Sets and notifies observers of state changes.
        subject.setState("State1");
        subject.setState("State2");
    }
}