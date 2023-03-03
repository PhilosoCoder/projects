package gym;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

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

    public Gym findGymById(long gymId) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.find(Gym.class, gymId);
        } finally {
            manager.close();
        }
    }

    public Trainer findTrainerById(long trainerId) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.find(Trainer.class, trainerId);
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

    public List<Trainer> listTrainersOfGym(long gymID) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            List<Trainer> trainers = manager.createQuery(
                            "select trainer " +
                                    "from Trainer trainer " +
                                    "left join fetch Gym gym " +
                                    "where trainer.gym.id = :gymId"
                            , Trainer.class)
                    .setParameter("gymId", gymID)
                    .getResultList();
            manager.getTransaction().commit();
            return trainers;
        } finally {
            manager.close();
        }
    }

    public List<Athlete> listAthleteByTrainer(long trainerID) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            return manager.createQuery(
                            "select a " +
                                    "from Athlete a " +
                                    "left join fetch a.trainers t " +
                                    "where t.id =:trainerID"
                            , Athlete.class)
                    .setParameter("trainerID", trainerID)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public void deleteGym(long gymId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Gym gym = manager.find(Gym.class, gymId);
            manager.remove(gym);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public void deleteTrainer(long trainerId) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Trainer trainer = manager.getReference(Trainer.class, trainerId);
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
            Athlete athlete = manager.find(Athlete.class, athleteId);
            athlete.getGym().getAthletes().remove(athlete);
            manager.remove(athlete);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }
}
