package gym;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Gym gym;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Athlete> athletes = new HashSet<>();

    private String name;

    @Enumerated(EnumType.STRING)
    private TrainingType type;

    public Trainer() {
    }

    public Trainer(String name, TrainingType type) {
        this.name = name;
        this.type = type;
    }

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
        athlete.getTrainers().add(this);
        athlete.setTrainer(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
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
