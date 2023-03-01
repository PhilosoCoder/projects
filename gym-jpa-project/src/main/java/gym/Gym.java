package gym;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "gyms")
public class Gym {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "gym", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Trainer> trainers = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Athlete> athletes = new ArrayList<>();

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

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
