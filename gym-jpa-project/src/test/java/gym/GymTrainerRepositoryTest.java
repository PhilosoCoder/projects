package gym;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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
    void testSaveGym() {
        Gym gym = new Gym("Fit Gym");
        gymRepository.saveGym(gym);

        assertNotNull(gym.getId());
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

        assertNotNull(athlete.getGym());
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

    @Test
    void testListTrainersOfGym() {
        Gym gym = new Gym("Fit Gym");
        Gym otherGym = new Gym("Biggest Gym");

        Trainer trainer = new Trainer("John", TrainingType.FUNCTIONAL);
        Trainer otherTrainer = new Trainer("Jack", TrainingType.FUNCTIONAL);

        gymRepository.saveGym(gym);
        gymRepository.saveGym(otherGym);

        gymRepository.saveTrainerToGym(gym.getId(), trainer);
        gymRepository.saveTrainerToGym(gym.getId(), otherTrainer);

        List<Trainer> gymTrainers = gymRepository.listTrainersOfGym(gym.getId());
        List<Trainer> otherGymTrainers = gymRepository.listTrainersOfGym(otherGym.getId());

        assertThat(gymTrainers)
                .hasSize(2)
                .extracting(Trainer::getName)
                .containsExactly("John", "Jack");
        assertEquals(0, otherGymTrainers.size());
    }

    @Test
    void testListAthletesByTrainer() {
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

        List<Athlete> trainerAthletes = gymRepository.listAthleteByTrainer(trainer.getId());
        List<Athlete> otherTrainerAthletes = gymRepository.listAthleteByTrainer(otherTrainer.getId());

        assertThat(trainerAthletes)
                .hasSize(2)
                .extracting(Athlete::getName)
                .containsExactly("Joshua", "Jane");
        assertThat(otherTrainerAthletes)
                .hasSize(1)
                .extracting(Athlete::getName)
                .containsExactly("Jane");
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

        Trainer result = trainerRepository.removeAthleteFromTrainer(athlete.getId(), trainer.getId());
        result = trainerRepository.removeAthleteFromTrainer(otherAthlete.getId(), trainer.getId());
        assertEquals(0, result.getAthletes().size());
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
}