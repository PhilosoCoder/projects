package hu.geralt.designpatterns.creational.singleton;

// Ensures a class has only one instance and provides a global point of access to it.
// Example: Logger class that logs messages for the entire application.

class Singleton {
    private static Singleton instance;

    // Private constructor to prevent instantiation from outside
    private Singleton() {}

    // Static method to get the singleton instance
    public static Singleton getInstance() {
        // Create a new instance if it doesn't exist
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }
}

// Client Code
public class SingletonPatternDemo {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        singleton.log("Logging a message...");
    }
}


