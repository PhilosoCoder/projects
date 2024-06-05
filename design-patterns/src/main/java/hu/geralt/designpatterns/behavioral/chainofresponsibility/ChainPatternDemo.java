package hu.geralt.designpatterns.behavioral.chainofresponsibility;

// Enables the decoupling of senders and receivers of requests.
// Example: Loggers handle messages based on their severity level,
// passing them down the chain as needed.

// Abstract class defining the structure of the logger chain and handling the log message propagation.
abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    // Sets the next logger in the chain.
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    // Logs the message based on the level and passes it to the next logger in the chain.
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    // Abstract method for writing the log message.
    abstract protected void write(String message);
}

// Concrete logger class handling logging to the console.
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    // Writes the log message to the standard console.
    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

// Concrete logger class handling logging to the error console.
class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    // Writes the log message to the error console.
    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

// Concrete logger class handling logging to a file.
class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }

    // Writes the log message to a file.
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

// Client
public class ChainPatternDemo {
    // Creates and configures the logger chain.
    private static Logger getChainOfLoggers() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.DEBUG);
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);

        // Configures the chain of responsibility.
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        // Gets the configured logger chain.
        Logger loggerChain = getChainOfLoggers();

        // Logs messages at different levels to demonstrate the chain pattern.
        loggerChain.logMessage(Logger.INFO, "This is an information.");
        loggerChain.logMessage(Logger.DEBUG, "This is a debug level information.");
        loggerChain.logMessage(Logger.ERROR, "This is an error information.");
    }
}


