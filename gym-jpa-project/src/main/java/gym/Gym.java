package gym;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "gyms")
public class Gym {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "gym", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Trainer> trainers = new HashSet<>();

    @OneToMany(mappedBy = "gym", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Athlete> athletes = new HashSet<>();

    public Gym() {
    }

    public Gym(String name) {
        this.name = name;
    }

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
        athlete.setGym(this);
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
        trainer.setGym(this);
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
}
