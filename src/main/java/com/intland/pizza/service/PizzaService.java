package com.intland.pizza.service;

import com.intland.pizza.helper.CSVHelper;
import com.intland.pizza.helper.KnapsackHelper;
import com.intland.pizza.model.BudgetResult;
import com.intland.pizza.persistence.model.Pizza;
import com.intland.pizza.persistence.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository repository;

    @Transactional
    public void save(MultipartFile file) {
        try {
            List<Pizza> pizzas = CSVHelper.csvToPizzas(file.getInputStream());
            repository.deleteAll();
            repository.saveAll(pizzas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BudgetResult getPizzasForBudget(int budget) {
        int maxValue = KnapsackHelper.getMaxValue(repository.findAll(), budget);
        List<Pizza> bestCombination = KnapsackHelper.getBestPizzaCombination(repository.findAll(), budget);
        BudgetResult budgetResult = new BudgetResult(bestCombination, maxValue);
        return budgetResult;
    }

    public List<Pizza> getAllPizza() {
        return repository.findAll();
    }
}
