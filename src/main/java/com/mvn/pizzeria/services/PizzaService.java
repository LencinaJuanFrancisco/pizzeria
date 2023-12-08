package com.mvn.pizzeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mvn.pizzeria.persistence.entity.PizzaEntity;
import com.mvn.pizzeria.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

   

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe "));
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public List<PizzaEntity> with(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> without(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        PageRequest pageRequest = PageRequest.of(page, elements);
        return pizzaRepository.findAll(pageRequest);
    }

     public Page<PizzaEntity> getAvailable(int page, int elements,String sortBy,String sortDirection) {
        
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, elements, sort);
        return pizzaRepository.findAllByAvailableTrue(pageRequest);
    }

    public PizzaEntity get(int idPizza) {

        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza) {
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        pizzaRepository.deleteById(idPizza);
    }
}