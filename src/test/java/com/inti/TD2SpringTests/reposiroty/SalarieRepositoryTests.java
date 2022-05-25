package com.inti.TD2SpringTests.reposiroty;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.TD2SpringTests.model.Salarie;
import com.inti.TD2SpringTests.repository.SalarieRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalarieRepositoryTests
{
	
	@Autowired
	SalarieRepository salarieRepository;
	
	Salarie salarie, salarie2, salarie1;
	
	@BeforeEach
	public void setUp()
	{
		salarie = new Salarie();
		salarie2 = new Salarie("Toto", "Titi", "tt@gmail.com");
		salarie1 = salarieRepository.save(salarie2);
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
	
	@Test
	public void TestGetSalarie()
	{
		//When  (recupère en base)
		Salarie toGetSalarie = salarieRepository.getReferenceById(salarie1.getId());
		
		//Then
		assertThat(toGetSalarie).isNotNull();
		assertThat(toGetSalarie.getId()).isEqualTo(salarie1.getId());
		assertThat(toGetSalarie.getNom()).isEqualTo("Toto");
		assertThat(toGetSalarie.getPrenom()).isEqualTo("Titi");
		assertThat(toGetSalarie.getEmail()).isEqualTo("tt@gmail.com");
	}
	
	@Test
//	@Rollback(false)
//	@Commit
	public void TestDeleteExceptionSalarie()
	{
		// Given		
//		Salarie salarie = salarieRepository.save(salarie2);
//		int id = salarie.getId();
//		System.out.println("salarié : " + salarie);
//		
//		// When		
//		salarieRepository.deleteById(id);
//		salarie = salarieRepository.getReferenceById(id);
//		System.out.println("salarié : " + salarie);
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {salarieRepository.deleteById(1);});
		
		// Then
//		assertThat(salarie).isNull();
	}
	
	@Test
	@Transactional(value = TxType.NEVER)
	public void TestDeleteSalarie()
	{
		try {
		// Given		
		
		int id = salarie1.getId();
		System.out.println("salarié : " + salarie1);
		
		Salarie s1 = salarieRepository.getReferenceById(id);
		assertThat(s1).isNotNull();
		
		// When		
		salarieRepository.delete(s1);
		salarie1 = salarieRepository.getReferenceById(id);
		System.out.println("salarié : " + salarie1);
		
		// Then
		assertThat(salarie1).isNull();
		}
		catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		}
	}
	
	@Test
	public void testUpdateSalarie()
	{
		// given
		Salarie s1 = salarieRepository.save(salarie2);
		
		// when
		s1.setNom("Ronaldo");
		s1.setPrenom("Cristiano");
		Salarie s2 = salarieRepository.save(s1);
		
		// then
		assertThat(s2).isNotNull();
		assertThat(s2.getNom()).isEqualTo("Ronaldo");
	}
	
	@Test
	public void testGetAllSalarie()
	{
		// given
		Salarie s1 = salarieRepository.save(new Salarie("Jean", "Claude", "jc@gmail.com"));
		Salarie s2 = salarieRepository.save(new Salarie("Louis", "Claude", "lc@gmail.com"));
		
		// when
		List<Salarie> lisSalaries = salarieRepository.findAll();
		
		// then
		assertThat(lisSalaries).isNotEmpty();
		assertThat(lisSalaries).hasSize(3);
		assertThat(lisSalaries.get(0).getClass()).hasSameClassAs(Salarie.class);
		assertThat(lisSalaries.get(1).toString()).hasToString(s1.toString());
		
	}
	
	@Test
	public void testGetSalarieByNom()
	{
		// given
		Salarie s1 = salarieRepository.save(new Salarie("Jean", "Claude", "jc@gmail.com"));
		
		// when
		Salarie salarieParNom = salarieRepository.findByNom("Jean");
		
		//then
		assertThat(salarieParNom).isNotNull();
		
		
	}
	
	
	
}
