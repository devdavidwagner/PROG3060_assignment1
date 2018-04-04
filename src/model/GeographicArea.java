//PROG3060-Exercise 3
// DAVID WAGNER - 7256506
//CREATED 3/22/2018
//FINISHED 3/23/2018

//GEOGRAPHIC AREA CLASS


package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GEOGRAPHICAREA", schema="APP")
public class GeographicArea {
	
	public GeographicArea() {};
	
	public GeographicArea(int code, int level, String name, int altCode) {
		this.setCode(code);
		this.setLevel(level);
		this.setName(name);
		this.setAltCode(altCode);
		
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GEOGRAPHICAREAID", nullable = false)
	private int geoAreaID;
	
	@Column(name="CODE", nullable = false)
	private int code;
	
	@Column(name="LEVEL", nullable = false)
	private int level;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="ALTERNATIVECODE", nullable = false)
	private int altCode;
	
	
	
	public int getGeoAreaID() {
		return geoAreaID;
	}
	public void setGeoAreaID(int geoAreaID) {
		this.geoAreaID = geoAreaID;
	}



	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getAltCode() {
		return altCode;
	}



	public void setAltCode(int altCode) {
		this.altCode = altCode;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public Set<Age> getAges() {
		return ages;
	}

	public void setAges(Set<Age> ages) {
		this.ages = ages;
	}
	
	@OneToMany(mappedBy = "geographicArea")
	private Set<Age> ages;
	
	public Set<Household> getHouseholds() {
		return households;
	}

	public void setHouseholds(Set<Household> households) {
		this.households = households;
	}

	@OneToMany(mappedBy = "geographicArea")
	private Set<Household> households;



}
