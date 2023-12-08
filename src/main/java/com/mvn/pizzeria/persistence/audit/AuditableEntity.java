package com.mvn.pizzeria.persistence.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditableEntity {

    // estas dos ultimas columnas se utilizan para auditoria con JPA DATA
        // el JSONIgnore es para que o se vea este dato en las respuestas de los endpoint
        @Column(name="created_date")
        @CreatedDate
        @JsonIgnore
        private LocalDateTime createdDate;

        @Column(name="modified_date")
        @LastModifiedDate
        @JsonIgnore     
        private LocalDateTime modifiedDate;
    
}
