package br.com.proj01.api.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PlanetaDTO implements Serializable{
		

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Long id;
		private String nome;
		private String clima;
		private String terreno;
		private int quantidadeFilmes;
		
		public PlanetaDTO() {}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		@NotEmpty(message="O nome do planeta  não pode ser nula")
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		@NotEmpty(message="O nome do clima  não pode ser nula")
		public String getClima() {
			return clima;
		}
		public void setClima(String clima) {
			this.clima = clima;
		}
		@NotEmpty(message="O nome do terreno  não pode ser nula")
		@Length(min=5, max=20, message="O Nome deve conter entre 3 e 30 caracteres")
		public String getTerreno() {
			return terreno;
		}
		public void setTerreno(String terreno) {
			this.terreno = terreno;
		}
		public int getQuantidadeFilmes() {
			return quantidadeFilmes;
		}
		public void setQuantidadeFilmes(int quantidadeFilmes) {
			this.quantidadeFilmes = quantidadeFilmes;
		}

}