//PROG3060-Exercise 3
// DAVID WAGNER - 7256506
//CREATED 3/22/2018
//FINISHED 3/23/2018

//CENSUS YEAR CLASS

package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CENSUSYEAR", schema="APP")
public class CensusYear {
	
	public CensusYear() {};
	
	public CensusYear(int censusYearID, int censusYear){
		
		this.censusYearID = censusYearID;
		this.censusYear = censusYear;
		
	}
	
	@Id
	@Column(name="CENSUSYEARID", nullable = false) 
	private int censusYearID;
	
	@Column(name="CENSUSYEAR", nullable = false)
	private int censusYear;
	
	public int getCensusYearID() {
		return censusYearID;
	}
	public void setCensusYearID(int censusYearID) {
		this.censusYearID = censusYearID;
	}
	public int getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(int censusYear) {
		this.censusYear = censusYear;
	}
	
	public Set<Age> getAges() {
		return ages;
	}

	public void setAges(Set<Age> ages) {
		this.ages = ages;
	}
	
	@OneToMany(mappedBy="ageGroup")
	private Set<Age> ages;
	
	public Set<Household> getHouseholds() {
		return households;
	}

	public void setHouseholds(Set<Household> households) {
		this.households = households;
	}

	@OneToMany(mappedBy="censusYear")
	private Set<Household> households;

}
