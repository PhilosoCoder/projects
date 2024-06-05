package hu.geralt.designpatterns.creational.builder;

// Separates the construction of a complex object from its representation
// so that the same construction process can create different representations.
// Example: Building a car with different configurations.

// Represents the product to be built.
class Car {
    private String engine;
    private int seats;

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getEngine() {
        return engine;
    }

    public int getSeats() {
        return seats;
    }
}

// Builder interface for building the car.
interface Builder {
    void setEngine(String engine);
    void setSeats(int seats);
    Car getResult();
}

// Concrete implementation of the Builder interface for building cars.
class CarBuilder implements Builder {
    private Car car;

    public CarBuilder() {
        this.car = new Car();
    }

    @Override
    public void setEngine(String engine) {
        car.setEngine(engine);
    }

    @Override
    public void setSeats(int seats) {
        car.setSeats(seats);
    }

    @Override
    public Car getResult() {
        return car;
    }
}

// Director class responsible for constructing different types of cars.
class Director {
    public void constructSportsCar(Builder builder) {
        builder.setEngine("Sport Engine");
        builder.setSeats(2);
    }

    public void constructSUV(Builder builder) {
        builder.setEngine("SUV Engine");
        builder.setSeats(5);
    }
}

// Client Code
public class BuilderPatternDemo {
    public static void main(String[] args) {
        Builder builder = new CarBuilder();

        Director director = new Director();

        director.constructSportsCar(builder);
        Car sportsCar = builder.getResult();
        System.out.println("Sports Car Engine: " + sportsCar.getEngine());
        System.out.println("Sports Car Seats: " + sportsCar.getSeats());

        director.constructSUV(builder);
        Car suv = builder.getResult();
        System.out.println("SUV Engine: " + suv.getEngine());
        System.out.println("SUV Seats: " + suv.getSeats());
    }
}


