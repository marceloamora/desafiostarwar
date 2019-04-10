package br.com.proj01.api.dto;

public class APIPlanetsDTO {
	private int count;
	public int getCount() {
		return count;
	}
	public String getNext() {
		return next;
	}
	public ResultsDTO getResults() {
		return results;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public void setResults(ResultsDTO results) {
		this.results = results;
	}
	private String next;
	private ResultsDTO results;

}



