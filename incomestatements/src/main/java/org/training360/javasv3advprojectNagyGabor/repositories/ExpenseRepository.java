package org.training360.javasv3advprojectNagyGabor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.training360.javasv3advprojectNagyGabor.model.Expense;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository <Expense, Long> {

    @Query("select e from Expense e" +
            " where (:type is null or e.expenseType = :type)" +
            " and (:limit is null or e.amount <= :limit)" +
            " order by e.expenseType, e.amount")
    List<Expense> findExpensesByTypeAndMaxPrice(Optional<ExpenseType> type, Optional<Integer> limit);
}
