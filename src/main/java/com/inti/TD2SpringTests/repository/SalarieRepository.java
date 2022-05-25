package com.inti.TD2SpringTests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.TD2SpringTests.model.Salarie;

@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Integer>
{

}
