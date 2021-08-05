package com.intland.pizza.web;

import com.intland.pizza.persistence.model.Pizza;
import com.intland.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/pizzas")
public class RestController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            pizzaService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message + " \"");
        }
    }


    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return new ResponseEntity<List<Pizza>>(pizzaService.getAllPizza(), HttpStatus.OK);
    }

}
