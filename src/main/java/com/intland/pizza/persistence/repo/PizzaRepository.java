package com.intland.pizza.persistence.repo;

import com.intland.pizza.persistence.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
