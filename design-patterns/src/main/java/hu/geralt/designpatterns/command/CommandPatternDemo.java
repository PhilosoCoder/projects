package hu.geralt.designpatterns.command;

//Encapsulates a request as an object, allowing parameterization of clients with queues,
//requests, and operations, which facilitates undoable operations and logging.
//Example: Encapsulating light operations into concrete commands and using an invoker to execute them,
//enabling flexible control over device actions.

// Command Interface: Declares an interface for executing operations.
interface Command {
    void execute();
}

//Receiver: Knows how to perform the operations associated with carrying out a request.
class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

// Concrete Command: Turns on the light.
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command: Turns off the light.
class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker: Asks the command to carry out the request.
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    // Asks the command to execute the associated action.
    public void pressButton() {
        command.execute();
    }
}

//Client
public class CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command turnOn = new TurnOnLightCommand(light);
        Command turnOff = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();

        // Sets and executes the "turn on" command.
        remote.setCommand(turnOn);
        remote.pressButton();

        // Sets and executes the "turn off" command.
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}

