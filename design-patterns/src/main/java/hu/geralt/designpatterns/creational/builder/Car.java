package hu.geralt.designpatterns.creational.builder;

// Using the Builder pattern makes sense only when your products
// are quite complex and require extensive configuration. The
// following two products are related, although they don't have
// a common interface.
class Car {
    // A car can have a GPS, trip computer and some number of
    // seats. Different models of cars (sports car, SUV,
    // cabriolet) might have different features installed or
    // enabled.
}

class Manual {
    // Each car should have a user manual that corresponds to
    // the car's configuration and describes all its features.
}

// The builder interface specifies methods for creating the
// different parts of the product objects.
interface Builder {

    void reset();

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setTripComputer(boolean tripComputer);

    void setGPS(boolean gps);

}

// The concrete builder classes follow the builder interface and
// provide specific implementations of the building steps. Your
// program may have several variations of builders, each
// implemented differently.
class CarBuilder implements Builder {

    private Car car;

    public CarBuilder() {
        reset();
    }

    public void reset() {
        car = new Car();
    }

    public void setSeats(int seats) {
        // Set the number of seats in the car.
    }

    public void setEngine(Engine engine) {
        // Install a given engine.
    }

    public void setTripComputer(boolean tripComputer) {
        // Install a trip computer.
    }

    public void setGPS(boolean gps) {
        // Install a global positioning system.
    }

    public Car getProduct() {
        Car product = car;
        reset();
        return product;
    }
}

// Unlike other creational patterns, builder lets you construct
// products that don't follow the common interface.
class CarManualBuilder implements Builder {

    private Manual manual;

    public CarManualBuilder() {
        reset();
    }

    public void reset() {
        manual = new Manual();
    }

    public void setSeats(int seats) {
        // Document car seat features.
    }

    public void setEngine(Engine engine) {
        // Add engine instructions.
    }

    public void setTripComputer(boolean tripComputer) {
        // Add trip computer instructions.
    }

    public void setGPS(boolean gps) {
        // Add GPS instructions.
    }

    public Manual getProduct() {
        // Return the manual and reset the builder.
        return manual;
    }

}

// The director is only responsible for executing the building
// steps in a particular sequence. It's helpful when producing
// products according to a specific order or configuration.
// Strictly speaking, the director class is optional, since the
// client can control builders directly.
class Director {
    // The director works with any builder instance that the
    // client code passes to it. This way, the client code may
    // alter the final type of the newly assembled product.
    // The director can construct several product variations
    // using the same building steps.
    public void constructSportsCar(Builder builder) {
        builder.reset();
        builder.setSeats(2);
        builder.setEngine(new SportEngine());
        builder.setTripComputer(true);
        builder.setGPS(true);
    }

    public void constructSUV(Builder builder) {
        // ...
    }

}

// The client code creates a builder object, passes it to the
// director and then initiates the construction process. The end
// result is retrieved from the builder object.
class Application {

    public void makeCar() {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar(carBuilder);
        Car car = carBuilder.getProduct();

        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual manual = manualBuilder.getProduct();
    }

}