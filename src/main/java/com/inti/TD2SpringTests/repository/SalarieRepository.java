package com.inti.TD2SpringTests.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.TD2SpringTests.model.Salarie;

@Repository
//@Transactional
public interface SalarieRepository extends JpaRepository<Salarie, Integer>
{

}
