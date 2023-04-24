package org.training360.javasv3advprojectNagyGabor.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.IncomeDto;
import org.training360.javasv3advprojectNagyGabor.dtos.UpdateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.exceptions.IncomeNotFoundException;
import org.training360.javasv3advprojectNagyGabor.mappers.IncomeMapper;
import org.training360.javasv3advprojectNagyGabor.model.Income;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;
import org.training360.javasv3advprojectNagyGabor.repositories.IncomeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeService {

    private IncomeRepository incomeRepository;

    private IncomeMapper incomeMapper;

    public List<IncomeDto> listIncomes(Optional<IncomeType> type, Optional<Integer> limit) {
        List<Income> incomes = incomeRepository.findIncomesByTypeAndMaxPrice(type, limit);
        return incomeMapper.toDtos(incomes);
    }

    public IncomeDto findIncomeById(long id) {
        Income income = getIncome(id);
        return incomeMapper.toDto(income);
    }

    private Income getIncome(long id) {
        return incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException(id));
    }

    @Transactional
    public IncomeDto changeIncomeAmountById(long id, UpdateIncomeCommand updateIncomeommand) {
        Income income = getIncome(id);
        income.setAmount(updateIncomeommand.getAmount());
        return incomeMapper.toDto(income);
    }

    @Transactional
    public IncomeDto createIncome(CreateIncomeCommand createIncomeCommand) {
        Income income = new Income(createIncomeCommand.getTitle(), createIncomeCommand.getAmount(),
                createIncomeCommand.getIncomeType(), createIncomeCommand.getDate());
        incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    public IncomeDto deleteIncome(long id) {
        Income income = getIncome(id);
        incomeRepository.delete(income);
        return incomeMapper.toDto(income);
    }
}
