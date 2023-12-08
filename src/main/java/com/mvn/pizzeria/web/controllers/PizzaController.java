package com.mvn.pizzeria.web.controllers;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvn.pizzeria.persistence.entity.PizzaEntity;
import com.mvn.pizzeria.services.PizzaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/list")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements) {
        try {

            Page<PizzaEntity> pizzas = pizzaService.getAll(page, elements);
            if (pizzas.isEmpty()) {
                // Devuelve un código 404 si no hay pizzas disponibles
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pizzas);
        } catch (Exception e) {
            log.error("Error al obtener todas las pizzas", e);
            // Devuelve un código 500 y un mensaje de error en caso de excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve una lista vacía en caso
                                                                                    // de error
        }
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
        try {
            PizzaEntity pizza = pizzaService.get(idPizza);
            System.out.println("---------- find --------------" + pizza);
            if (pizza == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pizzaService.get(idPizza));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PizzaEntity pizza) {

        try {
            if (pizza != null && pizza.getName() != null && !pizza.getName().isEmpty() && pizza.getPrice() != 0) {
                return ResponseEntity.ok(pizzaService.save(pizza));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al guardar la pizza");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> upDate(@RequestBody PizzaEntity pizza) {

        try {
            if (pizza.getIdPizza() != null && pizzaService.exists(pizza.getIdPizza())) {
                if (pizza.getName() != null && !pizza.getName().isEmpty() && pizza.getPrice() != 0) {
                    return ResponseEntity.ok(pizzaService.save(pizza));
                }
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al guardar la pizza");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza que desea actualizar no existe!!!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{idPizza}")
    public ResponseEntity<String> deletePizza(@PathVariable int idPizza) {

        try {
            if (pizzaService.exists(idPizza)) {
                pizzaService.delete(idPizza);
                return ResponseEntity.ok().body("Eliminado correctamente");
            }
            return ResponseEntity.badRequest().body("El elemento que desea eliminar no existe");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/hay")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{description}")
    public ResponseEntity<List<PizzaEntity>> with(@PathVariable String description) {
        return ResponseEntity.ok(pizzaService.with(description));
    }

    @GetMapping("/without/{description}")
    public ResponseEntity<List<PizzaEntity>> withOut(@PathVariable String description) {
        return ResponseEntity.ok(pizzaService.without(description));
    }
}
