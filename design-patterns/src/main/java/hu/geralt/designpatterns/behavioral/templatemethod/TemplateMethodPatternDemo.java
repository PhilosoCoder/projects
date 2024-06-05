package hu.geralt.designpatterns.behavioral.templatemethod;

// Defines the skeleton of an algorithm in the superclass
// but lets subclasses override specific steps of the algorithm without changing its structure.
// Example: Create a framework for different types of games, allowing subclasses like Cricket and Football
// to customize specific game steps while maintaining a consistent overall workflow.

abstract class Game {
    // Abstract method to initialize the game.
    abstract void initialize();

    // Abstract method to start playing the game.
    abstract void startPlay();

    // Abstract method to end the game.
    abstract void endPlay();

    // Template method that orchestrates the game's workflow.
    public final void play() {
        initialize();   // Calls the initialize method for setup.
        startPlay();    // Calls the startPlay method to begin the game.
        endPlay();      // Calls the endPlay method to finish the game.
    }
}

// Concrete class implementing the Game interface for cricket.
class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

// Concrete class implementing the Game interface for football.
class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

// Client Code
public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket(); // Creates a Cricket game.
        game.play();               // Plays the cricket game.
        System.out.println();
        game = new Football();     // Creates a Football game.
        game.play();               // Plays the football game.
    }
}

