package org.training360.javasv3advprojectNagyGabor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int amount;

    @Column(name = "income_type")
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Income(String title, int amount, IncomeType incomeType, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.incomeType = incomeType;
        this.date = date;
    }
}
