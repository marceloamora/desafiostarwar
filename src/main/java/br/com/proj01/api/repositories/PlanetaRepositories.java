package br.com.proj01.api.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.proj01.api.entities.Planeta;




public interface PlanetaRepositories extends JpaRepository<Planeta, Long> {
	
	@Transactional(readOnly=true)
	Planeta findByNome(String nome);
	
	

}
