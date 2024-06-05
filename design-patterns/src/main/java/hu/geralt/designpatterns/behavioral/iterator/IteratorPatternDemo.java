package hu.geralt.designpatterns.behavioral.iterator;

// Encapsulates a collection's traversal and provides a uniform interface to iterate over elements
// without exposing the underlying data structure, promoting separation of concerns and decoupling.
// Example: Utilizing an iterator to traverse a collection of names stored in a repository,
// abstracting away the iteration logic from the client code.

// Iterator Interface: Declares the interface for accessing and traversing elements.
interface Iterator {
    boolean hasNext();
    String next();
}

// Aggregate Interface: Declares an interface for creating an iterator object.
interface Aggregate {
    Iterator getIterator();
}

// Concrete Iterator: Implements the iterator interface.
class NameIterator implements Iterator {
    private String[] names;
    private int position = 0;

    public NameIterator(String[] names) {
        this.names = names;
    }

    @Override
    public boolean hasNext() {
        return position < names.length;
    }

    @Override
    public String next() {
        if (this.hasNext()) {
            return names[position++];
        }
        return null;
    }
}

// Concrete Aggregate: Implements the aggregate interface to return an instance of the concrete iterator.
class NameRepository implements Aggregate {
    private String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator(names);
    }
}

//Client
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        Iterator iterator = namesRepository.getIterator();

        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("Name : " + name);
        }
    }
}


