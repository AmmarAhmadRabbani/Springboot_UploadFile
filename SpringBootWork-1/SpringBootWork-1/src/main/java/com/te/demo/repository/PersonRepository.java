package com.te.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.demo.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
