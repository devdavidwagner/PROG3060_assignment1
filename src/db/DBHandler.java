//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018
//FINISH A2 V.20 4/6/2018

//DB HANDLER CLASS TO CONNECT TO DERBY DATABASE

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Age;
import model.AgeGroup;
import model.GeographicArea;
import model.Household;


public class DBHandler {
	
	private static String dbURL = "jdbc:derby://localhost:1527/CanadaCensusDB";
    private static Connection conn = null;
    
    public static final String CONNECTION_USER = "dwet";
    public static final String CONNECTION_PASSWORD = "password123";
    
    public static final String PERSISTENCE_UNIT_NAME = "PROG3060_DWET_A2";
    
   
    
	
	public static void main(String[] args)
	{
	   
	}
	

		
	//creates new db connection to derby
	@PersistenceContext
	EntityManagerFactory tempEntityManagerFactory = null;
	
	@PersistenceContext
	EntityManager tempEntityManager = null;
	
	
	//starts manager factory
	public EntityManager createConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		
	
		
		try {
				
				tempEntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				tempEntityManager = tempEntityManagerFactory.createEntityManager();
				tempEntityManager.getTransaction().begin();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	
	     return tempEntityManager;
		
	}
	
	//shutsdown connection entity manager
	public void closeConnection() {
		
		try {
			

            if (tempEntityManager != null)
            {

                tempEntityManager.close();

            }

            if (tempEntityManagerFactory != null)
            {

                tempEntityManagerFactory.close();

            }
			
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	} 
	
    private static Statement stmt = null;
    
    //gets all geo areas within certain heirachy 
    public List<GeographicArea> getAreasWithin(int level, int altCode)
    {
    	List<GeographicArea> geoAreasWithin = new ArrayList<>();
    	
    	String tempJPLSelectQuery = "SELECT g from GeographicArea g ORDER BY g.altCode DESC";
    	Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery);
    	
    	List<GeographicArea> geoAreas =  tempQuery.getResultList();
    	
    	
    	for(GeographicArea g : geoAreas) {
    		
    		if(level == 0)
    		{
    			if(g.getLevel() == level + 1)
    			{
	    	
	    				geoAreasWithin.add(g);
	    			
    			}
    		}
    		else if(level == 1)
    		{
    			if(g.getLevel() == level + 1)
    			{
	    			String altCodeSub = Integer.toString(g.getAltCode()).substring(0, 2);
	    			if(Integer.parseInt(altCodeSub) == altCode) {
	    				geoAreasWithin.add(g);
	    			}
    			}
    		}
    		else if(level == 2)
    		{
    			if(g.getLevel() == level + 1)
    			{
	    			String altCodeSub = Integer.toString(g.getAltCode()).substring(0, 5);
	    			if(Integer.parseInt(altCodeSub) == altCode) {
	    				geoAreasWithin.add(g);
	    			}
    			}
    		}
    		
    		
    	}
    	
    	
    	
    	return geoAreasWithin;
    }
    
    
    //returns total households based upon criteria given
    public int totalHouseholds(int geoAreaID)
    {
    	System.out.println("AREA ID" + geoAreaID);
    	int numberOfHouseholds = 0;
    	
    	try {
			
			String tempJPLSelectQuery = "SELECT h from Household h WHERE h.censusYear.censusYearID = :censusYear "
					+ "AND h.householdType.id = :typeID AND h.householdSize.id = :sizeID AND h.householdEarners.id = :earnerID "
					+ "AND h.totalIncome.id = :incomeID AND h.geographicArea.geoAreaID = :geoID AND h.householdsByAgeRange.id = :ageRange";
			
		    Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery)
		    		.setParameter("censusYear", 1)
		    		.setParameter("typeID", 4)
		    		.setParameter("sizeID", 3)
		    		.setParameter("earnerID", 3)
		    		.setParameter("incomeID", 15)
		    		.setParameter("geoID", geoAreaID)
		    		.setParameter("ageRange", (Integer) 7); 
		  
		
		   //numberOfHouseholds = tempQuery.getResultList().size();
		    List<Household> houses =  tempQuery.getResultList();
		   
		
		    numberOfHouseholds = houses.get(0).getNumberReported();
		    


		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

    	
    	
    	return numberOfHouseholds;
    }
    
    //returns age object depending on geographic Area
    public List<Age> getAgeByGeoAreaID(int geoAreaID) 
    {
    	List<Age>  tempAges = new ArrayList<Age>();
    	
		try {
							
				String tempJPLSelectQuery = "SELECT a from Age a WHERE a.geographicArea.geoAreaID = :id";
			    Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery).setParameter("id", geoAreaID); 
			  
			
			    tempAges = tempQuery.getResultList();

		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	
		  
    
    	 return tempAges;

    }
    
    //gets geographic areas sorted by heirachy level
    public List<GeographicArea> getGeographicAreaList(int level) 
    {
    	List <GeographicArea>  tempGeoAreaList = new ArrayList();
    	
		try {
							
				String tempJPLSelectQuery = "SELECT ga from GeographicArea ga WHERE ga.level = :level ORDER BY level";
			    Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery).setParameter("level", level); 
			  
			
			    tempGeoAreaList = tempQuery.getResultList();
			    System.out.println("hello" + tempGeoAreaList.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	
		  
    
    	 return tempGeoAreaList;

    }
    public GeographicArea getGeoArea(int id)
    {
    	GeographicArea ga = tempEntityManager.find(GeographicArea.class, id);
    	
    	return ga;
    }
    
    
    //returns age groups
    public List<Age> getAgeGroupList()
    {
    	List<Age> ageGroup = new ArrayList<>();
    	
    	String tempJPLSelectQuery = "SELECT a FROM Age a WHERE "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age1 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age2 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age3 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age4 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age5 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age6 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age7 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age8 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age9 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age10 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age11 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age12 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age13 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age14 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age15 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age16 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age17 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age18 OR "
    			+ "a.geographicArea.geoAreaID = 1 AND "
    			+ "a.ageGroup.ageGroupID = :age19";
    
    	//geoArea = 1
    	//age groupID
    	//3,9,22,28,34,40,46,52,58,70,76,83,89,95,101,108,114,120,126
    	
    	  Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery)
    			  .setParameter("age1", 3)
    			  .setParameter("age2", 9)
    			  .setParameter("age3", 22)
    			  .setParameter("age4", 28)
    			  .setParameter("age5", 34)
    			  .setParameter("age6", 40)
    			  .setParameter("age7", 46)
    			  .setParameter("age8", 52)
    			  .setParameter("age9", 58)
    			  .setParameter("age10", 70)
    			  .setParameter("age11", 76)
    			  .setParameter("age12", 83)
    			  .setParameter("age13", 89)
    			  .setParameter("age14", 95)
    			  .setParameter("age15", 101)
    			  .setParameter("age16", 108)
    			  .setParameter("age17", 114)
    			  .setParameter("age18", 120)
    			  .setParameter("age19", 126);
			  
			
    	  ageGroup = tempQuery.getResultList();
    	  System.out.println(ageGroup.size());
    	
    	  return ageGroup;
 
    	
    }
    
    public List<Household> getMedianIncomeList(){
    	
    	List<Household> medianList = new ArrayList<>();
    	

		String tempJPLSelectQuery = "SELECT h from Household h WHERE "
				+ "h.censusYear.censusYearID = :censusYear "
				+ "AND h.householdType.id = :typeID "
				+ "AND h.householdSize.id = :sizeID "
				+ "AND h.householdEarners.id = :earnerID "
				+ "AND h.geographicArea.level = :level "
				+ "AND h.householdsByAgeRange.id = :ageRange "
				+ "AND h.totalIncome.id = :income "
				+ "ORDER BY h.numberReported DESC";
		
	    Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery)
	    		.setParameter("censusYear", 1)
	    		.setParameter("typeID", 4)
	    		.setParameter("sizeID", 3)
	    		.setParameter("earnerID", 3)
	    		.setParameter("level", 1)
	    		.setParameter("ageRange", (Integer) 9)
	    		.setParameter("income", 22); 
	    
	    medianList = tempQuery.getResultList();

	
    	
    	return medianList;
    }
    
	
	
	

}
