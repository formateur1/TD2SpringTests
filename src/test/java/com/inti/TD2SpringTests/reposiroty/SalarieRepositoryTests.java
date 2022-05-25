package com.inti.TD2SpringTests.reposiroty;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.TD2SpringTests.model.Salarie;
import com.inti.TD2SpringTests.repository.SalarieRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalarieRepositoryTests
{
	
	@Autowired
	SalarieRepository salarieRepository;
	
	Salarie salarie, salarie2;
	
	@BeforeEach
	public void setUp()
	{
		salarie = new Salarie();
		salarie2 = new Salarie("Toto", "Titi", "tt@gmail.com");
	}
	
	@Test
	public void testSaveSalarie()
	{
		// Given
		
		// when
		Salarie savedSalarie = salarieRepository.save(salarie2);
		
		// then
		assertThat(savedSalarie).isNotNull();
		assertThat(savedSalarie.getId()).isGreaterThan(1);
	}
}
