package org.training360.javasv3advprojectNagyGabor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.training360.javasv3advprojectNagyGabor.model.Income;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("select i from Income i" +
            " where (:type is null or i.incomeType = :type)" +
            " and (:limit is null or i.amount <= :limit)" +
            " order by i.incomeType, i.amount")
    List<Income> findIncomesByTypeAndMaxPrice(Optional<IncomeType> type, Optional<Integer> limit);
}
