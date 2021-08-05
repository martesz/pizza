package com.intland.pizza.web;

import com.intland.pizza.model.BudgetResult;
import com.intland.pizza.model.Order;
import com.intland.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/order")
    public String pizzaListForm(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping("/order")
    public String moneySubmit(@ModelAttribute Order order, Model model) {
        BudgetResult budgetResult = pizzaService.getPizzasForBudget(order.getBudget());
        model.addAttribute("budgetResult", budgetResult);
        return "result";
    }

}
