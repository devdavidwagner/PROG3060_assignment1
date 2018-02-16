//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018

//GEOGRAPHIC AREA JAVA BEAN

package model;

import java.util.List;

public class GeoArea {
	
	public GeoArea(String altCode, String name, String code, String level, String population){
		
		this.altCode = altCode;
		this.name = name;
		this.code = code;
		this.level = level;
		this.population = population;
		
	}
	
	public GeoArea(){}
	
	
	
	private List<String> areasWithin;

	public String getAltCode() {
		return altCode;
	}

	public void setAltCode(String altCode) {
		this.altCode = altCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public List<String> getAreasWithin() {
		return areasWithin;
	}

	public void setAreasWithin(List<String> areasWithin) {
		this.areasWithin = areasWithin;
	}



	private String altCode;
	
	private String name;
	
	private String code;
	
	private String level;
	
	private String population;
	
	
	

}
