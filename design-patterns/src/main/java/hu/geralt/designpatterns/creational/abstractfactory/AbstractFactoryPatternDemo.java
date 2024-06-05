package hu.geralt.designpatterns.creational.abstractfactory;

// Provides an interface for creating families of related or dependent objects
// without specifying their concrete classes.
// Example: GUI factory that creates consistent UI elements for different operating systems.

// Abstract Factory interface
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory for Windows
class WinFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

// Concrete Factory for Mac
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Abstract Product interface for Button
interface Button {
    void paint();
}

// Concrete Product for Windows Button
class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Windows Button");
    }
}

// Concrete Product for Mac Button
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Mac Button");
    }
}

// Abstract Product interface for Checkbox
interface Checkbox {
    void paint();
}

// Concrete Product for Windows Checkbox
class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Windows Checkbox");
    }
}

// Concrete Product for Mac Checkbox
class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Mac Checkbox");
    }
}

// Client Code
public class AbstractFactoryPatternDemo {
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;

        // Configuration is determined based on runtime condition
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            factory = new WinFactory();
        } else {
            factory = new MacFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}

class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

