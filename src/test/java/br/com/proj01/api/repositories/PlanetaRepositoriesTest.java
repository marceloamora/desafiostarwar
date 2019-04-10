package br.com.proj01.api.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proj01.api.entities.Planeta;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlanetaRepositoriesTest {

	@Autowired
	PlanetaRepositories planetaRepositories;

	public static final String NOME_PLANETA = "Yavin IV";
	public static final String NOME_PLANETA_INEXISTENTE = "Yavin IV 2222";
	public static  Long ID = 0L;

/*	@Before
	public void iniciar() {
		Planeta planeta = new Planeta();
		planeta.setNome("Yavin IV");
		planeta.setClima("temperate, tropical");
		planeta.setTerreno("jungle, rainforests");
		Planeta planetaSalvo = this.planetaRepositories.save(planeta);
		ID = planetaSalvo.getId();
		
		
	}*/

/*	@After
	public void finalizar() {

		this.planetaRepositories.deleteAll();
	}
*/
	@Test
	public void criarPlanetaTest() {
		Planeta planeta = new Planeta();
		planeta.setNome("Yavin IV");
		planeta.setClima("temperate, tropical");
		planeta.setTerreno("jungle, rainforests");
		Planeta planetaSalvo = this.planetaRepositories.save(planeta);
		ID = planetaSalvo.getId();
		
	}

/*	@Test
	public void buscarPorNomeTest() {

		Planeta planeta = this.planetaRepositories.findByNome(NOME_PLANETA);
		assertEquals(NOME_PLANETA, planeta.getNome());

	}
	
	@Test
	public void buscarPorNomeInexistente() {
		Planeta planeta = this.planetaRepositories.findByNome(NOME_PLANETA_INEXISTENTE);
		assertNull(planeta);
	}*/
	
	@Test
	public void buscarPorID() {
		Planeta planeta = this.planetaRepositories.findOne(ID);
		assertNotNull(planeta); 
	}
	
	


}
