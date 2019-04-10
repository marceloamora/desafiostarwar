package br.com.proj01.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.proj01.api.dto.ResultsDTO;
import br.com.proj01.api.services.PlanetaService;

@SpringBootApplication
public class Proj01Application {
	
	@Autowired
	private  PlanetaService planetaService;

	public static void main(String[] args) {
		SpringApplication.run(Proj01Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			List<ResultsDTO> listarAPIPlanetas = this.planetaService.listarAPIPlanetas();
			PlanetasStatic.setPlanetas(listarAPIPlanetas);
		  
		};
	}

}
