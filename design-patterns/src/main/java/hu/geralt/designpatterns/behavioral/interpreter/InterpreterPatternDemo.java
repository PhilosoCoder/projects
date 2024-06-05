package hu.geralt.designpatterns.behavioral.interpreter;

// Provides a way to evaluate language grammar or expressions.
// Example: Build a syntax tree and interpret expressions based on the given context.

// Abstract Expression: Declares an interpret method to interpret expressions.
interface Expression {
    boolean interpret(String context);
}

// Terminal Expression: Implements the interpret method for terminal expressions in the language.
class TerminalExpression implements Expression {
    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}

// Non-terminal Expression: Implements the interpret method for non-terminal expressions,
// combining terminal expressions.
class OrExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

// Non-terminal Expression
class AndExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

// Context: Contains information that is global to the interpreter.
class Context {
    private final String input;

    public Context(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}

// Client Code
public class InterpreterPatternDemo {
    public static Expression buildInterpreterTree() {
        Expression terminal1 = new TerminalExpression("Alice");
        Expression terminal2 = new TerminalExpression("Bob");
        Expression terminal3 = new TerminalExpression("Charlie");
        Expression terminal4 = new TerminalExpression("David");

        // Constructing the syntax tree
        Expression or1 = new OrExpression(terminal1, terminal2);
        Expression or2 = new OrExpression(terminal3, terminal4);
        return new AndExpression(or1, or2);
    }

    public static void main(String[] args) {
        // Context with sample input
        Context context = new Context("Alice Charlie");

        // Building the interpreter tree
        Expression expression = buildInterpreterTree();

        // Interpreting the expression
        boolean result = expression.interpret(context.getInput());
        System.out.println("Interpreted result: " + result);
    }
}

