package hu.geralt.designpatterns.creational.factorymethod;

// The creator class declares the factory method that must
// return an object of a product class. The creator's subclasses
// usually provide the implementation of this method.
abstract class Dialog {

    // The creator may also provide some default implementation
    // of the factory method.
    abstract Button createButton();

    // Note that, despite its name, the creator's primary
    // responsibility isn't creating products. It usually
    // contains some core business logic that relies on product
    // objects returned by the factory method. Subclasses can
    // indirectly change that business logic by overriding the
    // factory method and returning a different type of product
    // from it.
    public void render() {
        // Call the factory method to create a product object.
        Button okButton = createButton();
        // Now use the product.
        okButton.onClick(this::closeDialog);
        okButton.render();
    }

    // Example method to be called on button click
    private void closeDialog() {
        System.out.println("Dialog closed.");
    }

}

// Concrete creators override the factory method to change the
// resulting product's type.
class WindowsDialog extends Dialog {

    @Override
    Button createButton() {
        return new WindowsButton();
    }

}

class WebDialog extends Dialog {

    @Override
    Button createButton() {
        return new HTMLButton();
    }

}

// The product interface declares the operations that all
// concrete products must implement.
interface Button {

    void render();

    void onClick(Runnable f);

}

// Concrete products provide various implementations of the
// product interface.
class WindowsButton implements Button {

    @Override
    public void render() {
        // Render a button in Windows style.
        System.out.println("Render Windows button.");
    }

    @Override
    public void onClick(Runnable f) {
        // Bind a native OS click event.
        f.run();
    }

}

class HTMLButton implements Button {

    @Override
    public void render() {
        // Return an HTML representation of a button.
        System.out.println("Render HTML button.");
    }

    @Override
    public void onClick(Runnable f) {
        // Bind a web browser click event.
        f.run();
    }

}

class Application {

    private Dialog dialog;

    // The application picks a creator's type depending on the
    // current configuration or environment settings.
    private void initialize() throws Exception {

        // Simulated configuration setting
        String configOS = readApplicationConfigFile();

        if (configOS.equals("Windows")) {
            dialog = new WindowsDialog();
        } else if (configOS.equals("Web")) {
            dialog = new WebDialog();
        } else {
            throw new Exception("Error! Unknown operating system.");
        }

    }

    // Simulated method to read configuration
    private String readApplicationConfigFile() {
        // This is just a placeholder. In a real application, this
        // would read from a configuration file or environment setting.
        return "Windows";  // Change this value to "Web" to test WebDialog
    }

    // The client code works with an instance of a concrete
    // creator, albeit through its base interface. As long as
    // the client keeps working with the creator via the base
    // interface, you can pass it any creator's subclass.
    public void main() throws Exception {
        this.initialize();
        dialog.render();
    }

    public static void main(String[] args) {
        try {
            Application app = new Application();
            app.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
