package br.com.proj01.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.proj01.api.PlanetasStatic;
import br.com.proj01.api.dto.PlanetaDTO;
import br.com.proj01.api.dto.ResultsDTO;
import br.com.proj01.api.entities.Planeta;
import br.com.proj01.api.response.Response;
import br.com.proj01.api.services.PlanetaService;

@RestController
@RequestMapping("api/planetas")
@CrossOrigin(origins = "*")
public class PlanetaController {

	@Autowired
	PlanetaService planetaService;

	private static final Logger logger = LoggerFactory.getLogger(PlanetaController.class);

	@PostMapping("/cadastrar")
	public ResponseEntity<Response<PlanetaDTO>> cadastrar(@Valid @RequestBody PlanetaDTO dto, BindingResult result) {
		logger.info("cadastrar");
		Response<PlanetaDTO> response = new Response<PlanetaDTO>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
				response.getErrors().add(error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(response);
		}
	
    	List<ResultsDTO> lista = PlanetasStatic.getPlanetas().stream()
				.filter((ResultsDTO p) -> p.getName().equalsIgnoreCase(dto.getNome()))
				.collect(Collectors.toList());
		if (lista == null) {
			response.getErrors().add("Não foi encontrado planeta com esse nome na API" + dto.getNome());
			return ResponseEntity.badRequest().body(response);
		}else {
			
			dto.setClima(lista.get(0).getClimate());
			dto.setTerreno(lista.get(0).getTerrain());
			dto.setQuantidadeFilmes(lista.get(0).getFilms().size());
		}
		Optional<Planeta> buscarPorNome = this.planetaService.buscarPorNome(dto.getNome());
		if(buscarPorNome.isPresent()) {
			response.getErrors().add("Planeta já foi cadastrado" + dto.getNome());
			return ResponseEntity.badRequest().body(response);
		}
	
		Planeta planeta = mapperToEntity(dto);
		Planeta planetaCadastrado = this.planetaService.salvar(planeta);
		response.setData(mapperToDTO(planetaCadastrado));
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping("/buscarNome/{id}")
	public ResponseEntity<Response<PlanetaDTO>> buscarPorNome(@PathVariable("id") String id) {
		logger.info("buscarPorNome");
		Response<PlanetaDTO> response = new Response<PlanetaDTO>();
		Optional<Planeta> planeta = this.planetaService.buscarPorNome(id);
		if (planeta.isPresent() == false) {
			response.getErrors().add("Planeta não encontrado para o nome : " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.mapperToDTO(planeta.get()));
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/{id}")
	public ResponseEntity<Response<PlanetaDTO>> buscarPorid(@PathVariable("id") Long id) {
		logger.info("buscarPorid");
		Response<PlanetaDTO> response = new Response<PlanetaDTO>();
		Optional<Planeta> planeta = this.planetaService.buscarPorID(id);
		if (planeta.isPresent() == false) {
			response.getErrors().add("Planeta não encontrado para o id : " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.mapperToDTO(planeta.get()));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<PlanetaDTO>> excluir(@PathVariable("id") Long id) {
		logger.info("excluir");
		Response<PlanetaDTO> response = new Response<PlanetaDTO>();
		Optional<Planeta> planeta = this.planetaService.buscarPorID(id);
		if (planeta.isPresent() == false) {
			response.getErrors().add("Planeta não encontrado para o id : " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.planetaService.excluir(id);
		response.setData(this.mapperToDTO(planeta.get()));
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/todos")
	public ResponseEntity<List<PlanetaDTO>> listarTodos() {
		Optional<List<Planeta>> listarTodos = this.planetaService.listarTodos();
		List<PlanetaDTO> planetas = new ArrayList<PlanetaDTO>();
		listarTodos.get().forEach(planeta -> {
			planetas.add(mapperToDTO(planeta));
		});

		return ResponseEntity.ok(planetas);
	}

	@RequestMapping("todos/starwar")
	public ResponseEntity<List<ResultsDTO>> listaApiStarWar() throws JsonParseException, IOException {
		List<ResultsDTO> listarAPIPlanetas = this.planetaService.listarAPIPlanetas();
		return ResponseEntity.ok(listarAPIPlanetas);
	}

	private PlanetaDTO mapperToDTO(Planeta planeta) {
		PlanetaDTO planetaDTO = new PlanetaDTO();
		planetaDTO.setClima(planeta.getClima());
		planetaDTO.setId(planeta.getId());
		planetaDTO.setNome(planeta.getNome());
		planetaDTO.setTerreno(planeta.getTerreno());
		planetaDTO.setQuantidadeFilmes(planeta.getQuantidadeFilmes());
		return planetaDTO;
	}

	private Planeta mapperToEntity(PlanetaDTO planetaDTO) {
		Planeta planeta = new Planeta();
		planeta.setClima(planetaDTO.getClima());
		planeta.setNome(planetaDTO.getNome());
		planeta.setTerreno(planetaDTO.getTerreno());
		planeta.setQuantidadeFilmes(planetaDTO.getQuantidadeFilmes());

		return planeta;
	}
}
