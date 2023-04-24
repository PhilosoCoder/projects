package org.training360.javasv3advprojectNagyGabor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_title")
    private String title;

    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type")
    private ExpenseType expenseType;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Expense(String title, int amount, ExpenseType expenseType, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.expenseType = expenseType;
        this.date = date;
    }
}
