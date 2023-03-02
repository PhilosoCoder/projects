package gym;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymTrainerRepositoryTest {

    GymRepository gymRepository;

    TrainerRepository trainerRepository;

    EntityManagerFactory factory;

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
    void saveTrainerToGym() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);

        Trainer result = gymRepository.saveTrainerToGym(gym.getId(), new Trainer("John", TrainingType.REHAB));

        assertNotNull(result.getId());
    }

    @Test
    void saveAthleteToGym() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);

        Athlete athlete = gymRepository.saveAthleteToGym(gym.getId(), new Athlete("Joe", TrainingType.BODYBUILDING));
    }

    @Test
    void testSaveAthleteToTrainer() {
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
    void testDeleteGym() {
        Gym gym = new Gym("Functional Gym");
        gymRepository.saveGym(gym);
        Gym otherGym = new Gym("Rehab Gym");
        gymRepository.saveGym(otherGym);

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        Athlete athlete = new Athlete("Bob", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);
        Athlete anotherAthlete = new Athlete("Jill", TrainingType.BODYBUILDING);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        otherTrainer.addAthlete(anotherAthlete);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(otherGym.getId(), otherTrainer);

        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), athlete.getId());

        gymRepository.deleteGym(gym.getId());
    }

    @Test
    void testDeleteTrainersGym() {
        Gym gym = new Gym("Fit Gym");
        Gym otherGym = new Gym("Biggest Gym");

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        Athlete athlete = new Athlete("Joshua", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        gymRepository.saveGym(gym);
        gymRepository.saveGym(otherGym);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(otherGym.getId(), otherTrainer);

        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), athlete.getId());

        trainerRepository.deleteTrainersGym(otherTrainer.getId());
    }

    @Test
    void testDeleteAthlete() {
        Gym gym = new Gym("Functional Gym");
        gymRepository.saveGym(gym);

        Athlete athlete = new Athlete("Bob", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);
        Athlete anotherAthlete = new Athlete("Jill", TrainingType.BODYBUILDING);

        gymRepository.saveAthleteToGym(gym.getId(), athlete);
        gymRepository.saveAthleteToGym(gym.getId(), otherAthlete);
        gymRepository.saveAthleteToGym(gym.getId(), anotherAthlete);

        gymRepository.deleteAthlete(athlete.getId());
    }

    @Test
    void deleteAthleteFromTrainer() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);

        Athlete athlete = new Athlete("Joshua", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        trainer.addAthlete(athlete);
        trainer.addAthlete(otherAthlete);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);

        trainerRepository.removeAthleteFromTrainer(athlete.getId(), trainer.getId());
        trainerRepository.removeAthleteFromTrainer(otherAthlete.getId(), trainer.getId());
        assertEquals(0, trainer.getAthletes().size());
    }

    @Test
    void testDeleteTrainer() {
        Gym gym = new Gym("Functional Gym");
        gymRepository.saveGym(gym);

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(gym.getId(), otherTrainer);

        gymRepository.deleteTrainer(trainer.getId());
    }

    @Test
    void testFindTrainersOfGym() {
        Gym gym = new Gym("Fit Gym");
        Gym otherGym = new Gym("Biggest Gym");

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        gymRepository.saveGym(gym);
        gymRepository.saveGym(otherGym);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(gym.getId(), otherTrainer);

        assertEquals(2, gymRepository.listTrainersOfGym(gym.getId()).size());
        assertEquals(0, gymRepository.listTrainersOfGym(otherGym.getId()).size());
    }

    @Test
    void testFindTrainersAndAthletes() {
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

    @Test
    void testFindAthletesByTrainer() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        Athlete athlete = new Athlete("Joshua", TrainingType.BODYBUILDING);
        Athlete otherAthlete = new Athlete("Jane", TrainingType.REHAB);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(gym.getId(), otherTrainer);

        gymRepository.saveAthleteToGym(gym.getId(), athlete);
        gymRepository.saveAthleteToGym(gym.getId(), otherAthlete);

        trainerRepository.saveAthleteToTrainer(trainer.getId(), athlete.getId());
        trainerRepository.saveAthleteToTrainer(trainer.getId(), otherAthlete.getId());

        trainerRepository.updateTrainerWithPersistedAthlete(otherTrainer.getId(), otherAthlete.getId());

        assertEquals(2, gymRepository.listAthleteByTrainer(trainer.getId()).size());
        assertEquals(1, gymRepository.listAthleteByTrainer(otherTrainer.getId()).size());
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
}