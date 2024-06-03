package hu.geralt.designpatterns.creational.singleton;

// The Database class defines the `getInstance` method that lets
// clients access the same instance of a database connection
// throughout the program.
class Database {

    // The singleton's constructor should always be private to
    // prevent direct construction calls with the `new`
    // operator.
    private Database() {
        // Some initialization code, such as the actual
        // connection to a database server.
        // ...
    }

    private static final class DatabaseHolder {
        // The field for storing the singleton instance should be
        // declared static and final.
        private static final Database instance = new Database();
    }

    // The static method that controls access to the singleton
    // instance.
    public static Database getInstance() {
        return DatabaseHolder.instance;
    }

    // Finally, any singleton should define some business logic
    // which can be executed on its instance.
    public void query(String sql) {
        // For instance, all database queries of an app go
        // through this method. Therefore, you can place
        // throttling or caching logic here.
        // ...
    }

}

class Application {

    public static void main(String[] args) {
        Database foo = Database.getInstance();
        foo.query("SELECT ...");
        // ...
        Database bar = Database.getInstance();
        bar.query("SELECT ...");
        // The variable `bar` will contain the same object as
        // the variable `foo`.
    }

}

