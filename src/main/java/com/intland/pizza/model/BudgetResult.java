package com.intland.pizza.model;

import com.intland.pizza.persistence.model.Pizza;

import java.util.List;

public class BudgetResult {
    private List<Pizza> pizzas;
    private int value;

    public BudgetResult(){

    }
    public BudgetResult(List<Pizza> pizzas, int value) {
        this.pizzas = pizzas;
        this.value = value;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
