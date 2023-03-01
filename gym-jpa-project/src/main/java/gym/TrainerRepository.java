package gym;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class TrainerRepository {

    private EntityManagerFactory factory;

    public TrainerRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Trainer saveTrainer(Trainer trainer) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(trainer);
            manager.getTransaction().commit();
            return trainer;
        } finally {
            manager.close();
        }
    }

    public List<Athlete> listAthleteByTrainer(long athleteID) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            return manager.createQuery("select a from Athlete a join fetch a.trainers where a.id = :athleteID", Athlete.class)
                    .setParameter("id", athleteID)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public void deleteTrainer(long trainerId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Trainer trainer = entityManager.getReference(Trainer.class, trainerId);
            entityManager.remove(trainer);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public Athlete saveAthleteToTrainer(long trainerId, long athleteId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Trainer trainer = manager.find(Trainer.class, trainerId);
            Athlete athlete = manager.find(Athlete.class, athleteId);
            trainer.addAthlete(athlete);
            manager.getTransaction().commit();
            return athlete;
        } finally {
            manager.close();
        }
    }

    public void removeAthleteFromTrainer(long athleteId, long trainerId){
        EntityManager em = factory.createEntityManager();
        try{
            em.getTransaction().begin();
            Trainer trainer = em.find(Trainer.class, trainerId);
            Athlete athlete = em.find(Athlete.class, athleteId);
            trainer.getAthletes().remove(athlete);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

    public void updateTrainerWithPersistedAthlete(long trainerId, long athleteId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Trainer trainer = manager.find(Trainer.class, trainerId);
            Athlete athlete = manager.find(Athlete.class, athleteId);
            trainer.addAthlete(athlete);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }
}
