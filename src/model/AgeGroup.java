//PROG3060-Exercise 3
// DAVID WAGNER - 7256506
//CREATED 3/22/2018
//FINISHED 3/23/2018

//AGE GROUP CLASS

package model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="AgeGroup", schema="APP")
public class AgeGroup {
	
	public AgeGroup() {};
	
	public AgeGroup(int ageGroupID, String description)
	{
		this.ageGroupID = ageGroupID;
		this.description = description;
	}
	
	@Id
	@Column(name="AGEGROUPID", nullable = false)
	private int ageGroupID;
	
	@Column(name="DESCRIPTION", nullable = false)
	private String description;
	
	public int getAgeGroupID() {
		return ageGroupID;
	}
	public void setAgeGroupID(int ageGroupID) {
		this.ageGroupID = ageGroupID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
  	public Set<Age> getAges() {
		return ages;
	}

	public void setAges(Set<Age> ages) {
		this.ages = ages;
	}
	
	@OneToMany(mappedBy="ageGroup")
	private Set<Age> ages;

}
