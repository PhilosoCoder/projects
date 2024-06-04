package hu.geralt.designpatterns.creational.builder;

//Separates the construction of a complex object from its representation
//so that the same construction process can create different representations.
//Example: Building a car with different configurations.

class Car {
    private String engine;
    private int seats;

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}

interface Builder {
    void setEngine(String engine);
    void setSeats(int seats);
    Car getResult();
}

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
