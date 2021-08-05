package com.intland.pizza.helper;

import com.intland.pizza.persistence.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class KnapsackHelper {


    public static int getMaxValue(List<Pizza> pizzas, int budget) {
        int dp[] = calculateKnapsack(pizzas, budget);
        return dp[budget];
    }

    private static int[] calculateKnapsack(List<Pizza> pizzas, int budget) {
        int dp[] = new int[budget + 1];

        for (int i = 0; i <= budget; i++) {
            for (int j = 0; j < pizzas.size(); j++) {
                int price = pizzas.get(j).getPrice();
                if (price <= i) {
                    int valueWithAddedPizza = dp[i - price] + price;
                    if (dp[i] < valueWithAddedPizza) {
                        dp[i] = valueWithAddedPizza;
                    }
                }
            }
        }
        return dp;
    }

    public static List<Pizza> getBestPizzaCombination(List<Pizza> pizzas, int budget) {
        int[] dp = calculateKnapsack(pizzas, budget);
        List<Pizza> result = new ArrayList<>();
        int cheapest = pizzas.get(0).getPrice();
        for (Pizza pizza : pizzas) {
            int price = pizza.getPrice();
            if (price < cheapest) {
                cheapest = price;
            }
        }
        path(budget, pizzas.size(), pizzas, dp, result, cheapest);
        return result;
    }

    private static void path(int budget, int n, List<Pizza> pizzas, int[] dp, List<Pizza> result, int cheapest) {
        if (budget < cheapest) {
            return;
        }
        int ans = 0, chosenItem = 0;
        for (int j = 0; j < n; j++) {
            int price = pizzas.get(j).getPrice();
            if (budget >= price) {
                int newAns = dp[budget - price] + price;
                if (newAns > ans) {
                    ans = newAns;
                    chosenItem = j;
                }
            }
        }
        result.add(pizzas.get(chosenItem));
        int price = pizzas.get(chosenItem).getPrice();
        path(budget - price, n, pizzas, dp, result, cheapest);
    }
}
