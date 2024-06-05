package hu.geralt.designpatterns.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

// Enables the restoration of an object to its previous state,
// often used alongside the Command pattern for undo operations.
// Example: Allowing the Originator object to save and restore its state using a Memento object,
// with the Caretaker responsible for managing the Memento instances without directly manipulating their contents.

// Memento class storing the state of the Originator object.
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator class that creates and restores mementos.
class Originator {
    private String state;

    // Sets the state of the originator.
    public void setState(String state) {
        this.state = state;
    }

    // Gets the state of the originator.
    public String getState() {
        return state;
    }

    // Saves the current state to a memento.
    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    // Restores the state from a memento.
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

// Caretaker class responsible for managing mementos.
class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    // Adds a memento to the list.
    public void add(Memento state) {
        mementoList.add(state);
    }

    // Gets a memento from the list by index.
    public Memento get(int index) {
        return mementoList.get(index);
    }
}

// Client
public class MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        // Sets and saves states to mementos.
        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());

        // Sets and saves another state to a memento.
        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());

        // Sets a new state without saving it to a memento.
        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        // Restores and prints the first saved state.
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("First saved State: " + originator.getState());

        // Restores and prints the second saved state.
        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
