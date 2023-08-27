package com.bdd.cocumber.bdd.repository;


import com.bdd.cocumber.bdd.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
