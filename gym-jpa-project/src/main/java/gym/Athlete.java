package gym;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "athletes")
    private Set<Trainer> trainers = new HashSet<>();

    @ManyToOne
    private Gym gym;

    @ManyToOne
    private Trainer trainer;

    private String name;

    @Enumerated(EnumType.STRING)
    private TrainingType type;

    public Athlete() {
    }

    public Athlete(String name, TrainingType type) {
        this.name = name;
        this.type = type;
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
        trainer.getAthletes().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
