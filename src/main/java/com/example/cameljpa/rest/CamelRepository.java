package com.example.cameljpa.rest;

import com.example.cameljpa.model.Camel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamelRepository extends JpaRepository<Camel, Long> {

}
