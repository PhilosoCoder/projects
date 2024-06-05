package hu.geralt.designpatterns.structural.proxy;

//The Proxy pattern acts as a placeholder for another object, controlling access to it.
//Example: The ProxyImage class manages access to RealImage instances,
//delaying their creation until necessary.

// Subject Interface
interface Image {
    void display();
}

// Real Subject: The actual object that the proxy represents
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy: Provides controlled access to the real subject
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Client
public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("test_image1.jpg");
        Image image2 = new ProxyImage("test_image2.jpg");

        // Image will be loaded from disk
        image1.display();
        // Image will not be loaded from disk
        image1.display();

        // Image will be loaded from disk
        image2.display();
        // Image will not be loaded from disk
        image2.display();
    }
}
