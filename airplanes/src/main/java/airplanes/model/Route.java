package airplanes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "date_of_flight")
    private LocalDate dateOfFlight;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    public Route(String departureCity, String arrivalCity, LocalDate dateOfFlight) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.dateOfFlight = dateOfFlight;
    }
}
