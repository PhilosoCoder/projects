package hu.geralt.designpatterns.behavioral.state;

// Allows an object to alter its behavior when its internal state changes,
// providing a way to manage state-specific behavior independently.
// Example: A context object transitioning between different states,
// triggering corresponding behaviors defined by concrete state objects.

// State Interface
interface State {
    // Performs an action based on the context's state.
    void doAction(Context context);
}

// Concrete States: Each subclass implements a behavior associated with a state of the Context.
class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}

class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}

// Context: Maintains an instance of a State subclass that defines the current state.
class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

// Client Code
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}
