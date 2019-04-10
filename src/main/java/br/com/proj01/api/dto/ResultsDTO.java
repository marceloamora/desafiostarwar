package br.com.proj01.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultsDTO {
	private String  name;
	private String climate;
	private  String terrain;
	private  String rotation_period;
	private  String orbital_period;
	private  String diameter;
	private  String gravity;
	private  String surface_water;
	private  String population;
	private  List<String> films = new ArrayList<String>();
	private  List<String> residents = new ArrayList<String>();
	private  String created;
	private  String edited;
	private  String url;

	
	 public ResultsDTO(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public List<String> getFilms() {
		return films;
	}
	public void setFilms(List<String> films) {
		this.films = films;
	}
	public String getRotation_period() {
		return rotation_period;
	}
	public String getOrbital_period() {
		return orbital_period;
	}
	public String getDiameter() {
		return diameter;
	}
	public String getGravity() {
		return gravity;
	}
	public String getSurface_water() {
		return surface_water;
	}
	public String getPopulation() {
		return population;
	}
	public List<String> getResidents() {
		return residents;
	}
	public String getCreated() {
		return created;
	}
	public String getEdited() {
		return edited;
	}
	public String getUrl() {
		return url;
	}
	public void setRotation_period(String rotation_period) {
		this.rotation_period = rotation_period;
	}
	public void setOrbital_period(String orbital_period) {
		this.orbital_period = orbital_period;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	public void setSurface_water(String surface_water) {
		this.surface_water = surface_water;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public void setResidents(List<String> residents) {
		this.residents = residents;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
