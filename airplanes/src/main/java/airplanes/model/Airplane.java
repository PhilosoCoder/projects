package airplanes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "airplanes")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "airplane_type")
    private AirplaneType airplaneType;

    @Column(name = "owner_airline")
    private String ownerAirline;

    @OneToMany(mappedBy = "airplane")
    private List<Route> routes = new ArrayList<>();

    public Airplane(AirplaneType airplaneType, String ownerAirline) {
        this.airplaneType = airplaneType;
        this.ownerAirline = ownerAirline;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }
}
