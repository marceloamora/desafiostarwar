package br.com.proj01.api.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.proj01.api.dto.ResultsDTO;
import br.com.proj01.api.entities.Planeta;



public interface PlanetaService {
	Optional<Planeta> buscarPorNome(String nome);
	Optional<Planeta> buscarPorID(Long id);
	Optional<List<Planeta>> listarTodos();
	Planeta salvar(Planeta planeta);
	List<ResultsDTO> listarAPIPlanetas() throws JsonParseException, IOException;
	void excluir(Long id);
	
}
