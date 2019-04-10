package br.com.proj01.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proj01.api.entities.Planeta;
import br.com.proj01.api.repositories.PlanetaRepositories;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlanetaServiceTest {
	
	@MockBean
	PlanetaRepositories planetaRepositories;
	
	@Autowired
	private PlanetaService planetaService;
	
	private static final String nome= "JOAO";
	
	
	@Before
	public void setup() throws Exception{
		BDDMockito.given(this.planetaRepositories.findByNome(Mockito.anyString())).willReturn(new Planeta());
		BDDMockito.given(this.planetaRepositories.save(Mockito.any(Planeta.class))).willReturn(new Planeta());
		
	}
	
	@Test
	public void testPesquisaPorNome() {
		Optional<Planeta> planeta = this.planetaService.buscarPorNome(nome);
		assertTrue(planeta.isPresent());
	}

	@Test
	public void testSalvar() {
		Planeta planeta = this.planetaService.salvar(new Planeta());
		assertNotNull(planeta);
	}
}
