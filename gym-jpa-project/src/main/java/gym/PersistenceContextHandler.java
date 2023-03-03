package gym;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class PersistenceContextHandler {

    private EntityManagerFactory factory;

    public PersistenceContextHandler(EntityManagerFactory entityManagerFactory) {
        this.factory = entityManagerFactory;
    }

    public void doInTransaction(Consumer<EntityManager> consumer) {
        EntityManager e = factory.createEntityManager();
        try {
            e.getTransaction().begin();
            consumer.accept(e);
            e.getTransaction().commit();
        } finally {
            e.close();
        }
    }

    public <T> T query(Function<EntityManager, T> function) {
        EntityManager manager = factory.createEntityManager();
        try {
            return function.apply(manager);
        } finally {
            manager.close();
        }
    }
}
