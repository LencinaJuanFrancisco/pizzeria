package com.mvn.pizzeria.persistence.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mvn.pizzeria.persistence.audit.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pizza")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor
public class PizzaEntity extends AuditableEntity{
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_pizza",nullable = false)
        private Integer idPizza;
    
        @Column(nullable = false,unique = true,length = 30)
        private String name;
    
        @Column(nullable = false,length = 150)
        private String description;
    
        @Column(nullable = false, columnDefinition = "Decimal(5,2)")
        private double price;
    
        @Column(columnDefinition = "TINYINT")
        private Boolean vegetarian;
    
        @Column(columnDefinition = "TINYINT")
        private Boolean vegan;
    
        @Column(columnDefinition = "TINYINT",nullable = false)
        private Boolean available;


        
}