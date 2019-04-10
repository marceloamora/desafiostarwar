package br.com.proj01.api;

import java.util.ArrayList;
import java.util.List;

import br.com.proj01.api.dto.ResultsDTO;

public class PlanetasStatic {
	
	private static List<ResultsDTO> listaPlanetasAPI = new ArrayList<ResultsDTO>();
	
	public static void  setPlanetas(List<ResultsDTO>  lista) {
		listaPlanetasAPI = lista;
	}
	
	public static List<ResultsDTO> getPlanetas(){
		return listaPlanetasAPI;
	}

}
