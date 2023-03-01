package gym;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Set;

public class GymRepository {

    private EntityManagerFactory factory;

    public GymRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Gym saveGym(Gym gym) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(gym);
            manager.getTransaction().commit();
            return gym;
        } finally {
            manager.close();
        }
    }

    public Athlete saveAthleteToGym(long gymId, Athlete athlete) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Gym gym = manager.find(Gym.class, gymId);
            gym.addAthlete(athlete);
            manager.getTransaction().commit();
            return athlete;
        } finally {
            manager.close();
        }
    }

    public Trainer saveTrainerToGym(long gymId, Trainer trainer) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Gym gym = manager.find(Gym.class, gymId);
            gym.addTrainer(trainer);
            manager.getTransaction().commit();
            return trainer;
        } finally {
            manager.close();
        }
    }

    public Trainer findTrainerById(long trainerId) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Trainer.class, trainerId);
        } finally {
            em.close();
        }
    }

    public Gym findGymWithTrainersByTrainingType(long gymId, TrainingType trainingType) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select gym " +
                                    "from Gym gym " +
                                    "left join fetch gym.trainers trainers " +
                                    "where trainers.gym.id = :gymId " +
                                    "and trainers.type = :trainingType"
                            , Gym.class)
                    .setParameter("gymId", gymId)
                    .setParameter("trainingType", trainingType)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public Athlete findAthleteById(long athleteId) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.find(Athlete.class, athleteId);
        } finally {
            manager.close();
        }
    }

    public List<Trainer> listTrainers() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            return manager.createQuery(
                            "select t from Trainer t"
                            , Trainer.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public void deleteTrainer(long trainerId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Trainer trainer = findTrainerById(trainerId);
            manager.remove(trainer);
            trainer.getGym().getTrainers().remove(trainer);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public void deleteAthlete(long athleteId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Athlete athlete = findAthleteById(athleteId);
            manager.remove(athlete);
            athlete.getGym().getAthletes().remove(athlete);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public void deleteAthletesTrainer(long trainerId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Trainer trainer = findTrainerById(trainerId);
            trainer.setGym(null);
            manager.getTransaction().commit();
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

    public Gym findGymWithTrainersAndAthletes(long gymId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Gym gym = manager.createQuery(
                            "select gym " +
                                    "from Gym gym " +
                                    "left join fetch gym.trainers trainers " +
                                    "where gym.id=:gymId"
                            , Gym.class)
                    .setParameter("gymId", gymId)
                    .getSingleResult();
            List<Trainer> trainers = gym.getTrainers();
            manager.createQuery(
                            "select distinct trainer " +
                                    "from Trainer trainer " +
                                    "left join fetch trainer.athletes athletes " +
                                    "where trainer " +
                                    "in :trainers"
                            , Trainer.class)
                    .setParameter("trainers", trainers)
                    .getResultList();
            manager.getTransaction().commit();
            return gym;
        } finally {
            manager.close();
        }
    }
}
