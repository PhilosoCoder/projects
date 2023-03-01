package gym;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymRepositoryTest {

    GymRepository gymRepository;

    TrainerRepository trainerRepository;

    EntityManagerFactory factory;

    EntityManager manager;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        gymRepository = new GymRepository(factory);
        trainerRepository = new TrainerRepository(factory);
    }

    @AfterEach
    void close() {
        factory.close();
    }

    @Test
    void saveTrainer() {
        Trainer result = trainerRepository.saveTrainer(new Trainer("John", TrainingType.REHAB));

        assertNotNull(result.getId());
    }

    @Test
    void testSaveAthleteWithGymId() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);
        Athlete athlete = new Athlete("Jack", TrainingType.FUNCTIONAL);
        gymRepository.saveAthleteToGym(gym.getId(), athlete);

        Trainer trainer = gymRepository.saveTrainerToGym(gym.getId(), new Trainer("John", TrainingType.FUNCTIONAL));
        Athlete otherAthlete = trainerRepository.saveAthleteToTrainer(trainer.getId(), athlete.getId());

        Athlete result = gymRepository.findAthleteById(otherAthlete.getId());

        assertNotNull(result.getId());
        assertEquals("John", result.getTrainer().getName());
    }

    @Test
    void testSaveGymWithTrainers() {
        Gym gym = new Gym("Fit Gym");
        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Bill", TrainingType.BODYBUILDING);

        Athlete athlete = new Athlete("Bob", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        gymRepository.saveGym(gym);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(gym.getId(), otherTrainer);
        trainerRepository.updateTrainerWithPersistedAthlete(trainer.getId(), athlete.getId());
        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), athlete.getId());
        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), otherAthlete.getId());
    }

    @Test
    void testFindGymWithTrainersTrainingType() {
        Gym gym = new Gym("Functional Gym");
        gymRepository.saveGym(gym);

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);
        Trainer anotherTrainer = new Trainer("Joe", TrainingType.REHAB);

        gymRepository.saveTrainerToGym(
                gym.getId(),
                trainer);

        gymRepository.saveTrainerToGym(
                gym.getId(),
                otherTrainer);

        gymRepository.saveTrainerToGym(
                gym.getId(),
                anotherTrainer);

        Gym result = gymRepository.findGymWithTrainersByTrainingType(
                gym.getId(),
                TrainingType.REHAB);

        assertEquals(1, result.getTrainers().size());
        assertEquals(TrainingType.REHAB, result.getTrainers().get(0).getType());
    }

    @Test
    void testDeleteAthletesTrainer() {
        Gym gym = new Gym("Fit Gym");
        Gym otherGym = new Gym("Biggest Gym");
        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);
        Athlete athlete = new Athlete("Joshua", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        gymRepository.saveGym(gym);
        gymRepository.saveGym(otherGym);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(otherGym.getId(), otherTrainer);

        gymRepository.deleteAthletesTrainer(trainer.getId());
    }

    @Test
    void testFindGymWithTrainersAndAthletes() {
        Gym gym = new Gym("Fit Gym");
        Gym otherGym = new Gym("Biggest Gym");
        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);
        Athlete athlete = new Athlete("Joshua", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        gymRepository.saveGym(gym);
        gymRepository.saveGym(otherGym);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(otherGym.getId(), otherTrainer);

        trainerRepository.saveAthleteToTrainer(trainer.getId(), athlete.getId());
        trainerRepository.saveAthleteToTrainer(otherTrainer.getId(), otherAthlete.getId());

        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), otherAthlete.getId());

        System.out.println(gymRepository.findGymWithTrainersAndAthletes(1L));
    }

}