package hu.geralt.designpatterns.creational.singleton;

//Ensures a class has only one instance and provides a global point of access to it.
//Example: Logger class that logs messages for the entire application.

class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }
}


