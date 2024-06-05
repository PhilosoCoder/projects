package hu.geralt.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

// Composes objects into tree structures to represent part-whole hierarchies.
// Example: Files and directories in a file system.

// Component interface
interface Component {
    void operation();
}

// Leaf: Represents leaf objects that have no children.
class Leaf implements Component {
    @Override
    public void operation() {
        System.out.println("Leaf: Performing operation");
    }
}

// Composite: Represents composite objects that may contain leaf or other composite objects.
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }
}

// Client Code
public class CompositePatternDemo {
    public static void main(String[] args) {
        Composite root = new Composite();
        Composite directory1 = new Composite();
        Composite directory2 = new Composite();
        Leaf file1 = new Leaf();
        Leaf file2 = new Leaf();

        root.add(directory1);
        root.add(directory2);
        directory1.add(file1);
        directory2.add(file2);

        root.operation();
    }
}


