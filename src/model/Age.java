//PROG3060-Exercise 2
// DAVID WAGNER - 7256506
//CREATED 2/22/2018
//FINISHED 2/23/2018

//AGE CLASS

package model;

import javax.persistence.*;

@Entity
@Table(name="AGE", schema="APP")
public class Age {
	
	
	public Age() {};
	
	public Age(AgeGroup ageGroup, CensusYear censusYear, GeographicArea geographicArea, int combined, int female, int male)
	{
	
		this.ageGroup = ageGroup;
		this.censusYear = censusYear;
		this.geographicArea = geographicArea;
		this.combined = combined;
		this.female = female;
		this.male = male;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ageID", nullable = false)
	private int ageID;
	
	@ManyToOne
	@JoinColumn(name="GEOGRAPHICAREA")
	private GeographicArea geographicArea;
	
	@ManyToOne
	@JoinColumn(name="AGEGROUP")
	private AgeGroup ageGroup;
	
	@ManyToOne
	@JoinColumn(name="CENSUSYEAR")
	private CensusYear censusYear;
	
	@Column(name="COMBINED", nullable = false)
	private int combined;
	
	@Column(name="FEMALE", nullable = false)
	private int female;
	
	@Column(name="MALE", nullable = false)
	private int male;
	
	public int getAgeID() {
		return ageID;
	}
	public void setAgeID(int ageID) {
		this.ageID = ageID;
	}
	public CensusYear getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}
	public GeographicArea getGeoArea() {
		return geographicArea;
	}
	public void setGeoArea(GeographicArea geographicArea) {
		this.geographicArea = geographicArea;
	}
	public AgeGroup getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}
	public int getCombined() {
		return combined;
	}
	public void setCombined(int combined) {
		this.combined = combined;
	}
	public int getFemale() {
		return female;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	public int getMale() {
		return male;
	}
	public void setMale(int male) {
		this.male = male;
	}
	
	
}
