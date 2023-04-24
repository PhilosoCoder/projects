package org.training360.javasv3advprojectNagyGabor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateExpenseCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.CreateIncomeCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.CreatePersonCommand;
import org.training360.javasv3advprojectNagyGabor.dtos.PersonDto;
import org.training360.javasv3advprojectNagyGabor.exceptions.PersonNotFoundException;
import org.training360.javasv3advprojectNagyGabor.mappers.PersonMapper;
import org.training360.javasv3advprojectNagyGabor.model.Expense;
import org.training360.javasv3advprojectNagyGabor.model.Income;
import org.training360.javasv3advprojectNagyGabor.model.Person;
import org.training360.javasv3advprojectNagyGabor.repositories.ExpenseRepository;
import org.training360.javasv3advprojectNagyGabor.repositories.IncomeRepository;
import org.training360.javasv3advprojectNagyGabor.repositories.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    private IncomeRepository incomeRepository;

    private ExpenseRepository expenseRepository;

    private PersonMapper personMapper;

    @Transactional
    public PersonDto createPerson(CreatePersonCommand createPersonCommand) {
        Person person = personRepository.save(new Person(createPersonCommand.getName()));
        return personMapper.toDto(person);
    }

    @Transactional
    public PersonDto addIncomeToPerson(long id, CreateIncomeCommand createIncomeCommand) {
        Person person = getPerson(id);
        Income income = new Income(createIncomeCommand.getTitle(), createIncomeCommand.getAmount(),
                createIncomeCommand.getIncomeType(), createIncomeCommand.getDate());
        income.setPerson(person);
        person.addIncome(income);

        incomeRepository.save(income);
        return personMapper.toDto(person);
    }

    private Person getPerson(long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Transactional
    public PersonDto addExpenseToPerson(long id, CreateExpenseCommand createExpenseCommand) {
        Person person = getPerson(id);
        Expense expense = expenseRepository.save(new Expense(createExpenseCommand.getTitle(), createExpenseCommand.getAmount(),
                createExpenseCommand.getExpenseType(), createExpenseCommand.getDate()));
        expenseRepository.save(expense);
        person.addExpense(expense);
        return personMapper.toDto(person);
    }
}
