package hu.geralt.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

//Facilitates communication between colleague objects by encapsulating their interaction
//within a mediator interface, promoting loose coupling and centralized control.
//Example: Allowing users to send messages to each other through a central chat mediator,
//enabling seamless communication while abstracting away direct interactions between users.

// Mediator Interface: Declares an interface for communicating with colleague objects.
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

// Concrete Mediator: Implements cooperative behavior by coordinating Colleague objects.
class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    // Initializes the mediator with an empty list of users.
    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    // Adds a user to the list of users.
    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    // Sends a message to all users except the sender.
    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}

// Colleague Class: Each Colleague class knows its Mediator object and communicates with it
// when it would otherwise communicate with another colleague.
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    // Initializes a user with a mediator and a name.
    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    // Abstract method to send a message.
    public abstract void send(String msg);

    // Abstract method to receive a message.
    public abstract void receive(String msg);
}

// Concrete Colleague: Implements a user who can send and receive messages.
class UserImpl extends User {
    // Initializes a user with a mediator and a name.
    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    // Sends a message via the mediator.
    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    // Receives a message.
    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message=" + msg);
    }
}

//Client
public class MediatorPatternDemo {
    public static void main(String[] args) {
        // Creates a chat mediator.
        ChatMediator mediator = new ChatMediatorImpl();

        // Creates users and adds them to the mediator.
        User user1 = new UserImpl(mediator, "John");
        User user2 = new UserImpl(mediator, "Doe");
        User user3 = new UserImpl(mediator, "Smith");
        User user4 = new UserImpl(mediator, "Alice");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        // User1 sends a message to all users.
        user1.send("Hi All");
    }
}

