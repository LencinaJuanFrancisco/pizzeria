package com.mvn.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
// Importante recalcar que una "Projection" y un "DTO" (Data Transfer Object) no son lo mismo, aunque pueden ser utilizados para objetivos similares.

// ¿Cuándo usar cuál?

// Projection: Útil cuando solo necesitas una vista simplificada de tu entidad y estás usando Spring Data JPA. También es útil para optimizar consultas. DTO: Es recomendado cuando necesitas más flexibilidad o cuando estás combinando datos de varias fuentes. También es una buena elección cuando se desea aislar a los consumidores de la API de los cambios en las entidades subyacentes.