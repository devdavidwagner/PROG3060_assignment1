//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018

//AGE GROUP JAVA BEAN


package model;

public class AgeGroup {

	public AgeGroup(String desc, String malePop, String femalePop, String censusYear) {
		this.desc = desc;
		this.malePop = malePop;
		this.femalePop = femalePop;
		this.setCensusYear(censusYear);
		
	}
	
	public AgeGroup() {};
	
	private String censusYear;
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMalePop() {
		return malePop;
	}

	public void setMalePop(String malePop) {
		this.malePop = malePop;
	}

	public String getFemalePop() {
		return femalePop;
	}

	public void setFemalePop(String femalePop) {
		this.femalePop = femalePop;
	}

	public String getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(String censusYear) {
		this.censusYear = censusYear;
	}

	private String desc;
	
	private String malePop;
	 
	private String femalePop;
	
}
