package gym;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "athletes", cascade = {CascadeType.PERSIST})
    private List<Trainer> trainers = new ArrayList<>();

    @ManyToOne
    private Gym gym;

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

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
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
}
