package br.com.proj01.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.proj01.api.dto.ResultsDTO;
import br.com.proj01.api.entities.Planeta;
import br.com.proj01.api.repositories.PlanetaRepositories;



@Service
public class PlanetaServiceImpl implements PlanetaService {
    private static Logger logger = LoggerFactory.getLogger(PlanetaServiceImpl.class);
    @Autowired
    private PlanetaRepositories planetaRepositories;
	@Override
	public Optional<Planeta> buscarPorNome(String nome) {
		logger.info("buscar planeta por nome");
		return Optional.ofNullable(this.planetaRepositories.findByNome(nome));
	}

	@Override
	public Optional<Planeta> buscarPorID(Long id) {
		logger.info("buscar planeta por id");
		return Optional.ofNullable(this.planetaRepositories.findOne(id));
	}

	@Override
	public Optional<List<Planeta>> listarTodos() {
		logger.info("listar todos os planetas");
		return Optional.ofNullable(this.planetaRepositories.findAll());

	}

	@Override
	public Planeta salvar(Planeta planeta) {
		logger.info("salvar um planeta");
		return this.planetaRepositories.save(planeta);
	}

	@Override
	public void excluir(Long id) {
		logger.info("excluir um planeta");
		 this.planetaRepositories.delete(id);
		
	}

	@Override
	public List<ResultsDTO> listarAPIPlanetas() throws JsonParseException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = "https://swapi.co/api/planets/";

		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String b = res.getBody();
		ObjectMapper mapper = new ObjectMapper();

		JsonFactory factory = mapper.getFactory();
		JsonParser parser = factory.createParser(b);
		JsonNode actualObj = mapper.readTree(parser);
		JsonNode jsonNode = actualObj.get("results");
		List<ResultsDTO> lista = new ArrayList<ResultsDTO>();

		jsonNode.forEach(e -> {
			ResultsDTO umDTO = new ResultsDTO();
			umDTO.setName(e.get("name").textValue());
			umDTO.setClimate(e.get("climate").textValue());
			umDTO.setTerrain(e.get("terrain").textValue());
			umDTO.setRotation_period(e.get("rotation_period").textValue());
			umDTO.setOrbital_period(e.get("orbital_period").textValue());
			umDTO.setDiameter(e.get("diameter").textValue());
			umDTO.setGravity(e.get("gravity").textValue());
			umDTO.setSurface_water(e.get("surface_water").textValue());
			umDTO.setPopulation(e.get("population").textValue());
			umDTO.setCreated(e.get("created").textValue());
			umDTO.setEdited(e.get("edited").textValue());
			umDTO.setUrl(e.get("url").textValue());

			JsonNode filmes = e.get("films");

			filmes.forEach(o -> {
				umDTO.getFilms().add(o.textValue());
			});

			JsonNode residents = e.get("residents");
			residents.forEach(o -> {
				umDTO.getResidents().add(o.textValue());
			});

			lista.add(umDTO);
		});
		
		return lista;
	}

	

}
