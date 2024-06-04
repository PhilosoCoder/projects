package hu.geralt.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

//Composes objects into tree structures to represent part-whole hierarchies.
//Example: Files and directories in a file system.

interface Component {
    void operation();
}

class Leaf implements Component {
    @Override
    public void operation() {
        // ...
    }
}

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

