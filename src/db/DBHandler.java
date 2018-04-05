//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018

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
	
	//shutsdown connection to derby database
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
    
    public List<String> getAreasWithin(int level, int altCode)
    {
    	List<String> geoAreasWithin = new ArrayList<>();
    	
    	String tempJPLSelectQuery = "SELECT g from GeographicArea g";
    	Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery);
    	
    	List<GeographicArea> geoAreas =  tempQuery.getResultList();
    	
    	for(GeographicArea g : geoAreas) {
    		
    		if(level == 0)
    		{
    			if(g.getLevel() > 0)
    			{
	    			String altCodeSub = Integer.toString(g.getAltCode()).substring(0, 1);
	    			if(Integer.parseInt(altCodeSub) == altCode) {
	    				geoAreasWithin.add(g.getName());
	    			}
    			}
    		}
    		else if(level == 1)
    		{
    			if(g.getLevel() > 1)
    			{
	    			String altCodeSub = Integer.toString(g.getAltCode()).substring(0, 2);
	    			if(Integer.parseInt(altCodeSub) == altCode) {
	    				geoAreasWithin.add(g.getName());
	    			}
    			}
    		}
    		else if(level == 2)
    		{
    			if(g.getLevel() > 2)
    			{
	    			String altCodeSub = Integer.toString(g.getAltCode()).substring(0, 5);
	    			if(Integer.parseInt(altCodeSub) == altCode) {
	    				geoAreasWithin.add(g.getName());
	    			}
    			}
    		}
    		
    		
    	}
    	
    	
    	
    	return geoAreasWithin;
    }
    
    
    public int totalHouseholds(int geoAreaID)
    {
    	System.out.println("AREA ID" + geoAreaID);
    	int numberOfHouseholds = 0;
    	
    	try {
			
			String tempJPLSelectQuery = "SELECT h from Household h WHERE h.censusYear.censusYearID = :censusYear "
					+ "AND h.householdType.id = :typeID AND h.householdSize.id = :sizeID AND h.householdEarners.id = :earnerID "
					+ "AND h.totalIncome.id = :incomeID AND h.geographicArea.geoAreaID = :geoID";
			
		    Query tempQuery = tempEntityManager.createQuery(tempJPLSelectQuery)
		    		.setParameter("censusYear", 1)
		    		.setParameter("typeID", 4)
		    		.setParameter("sizeID", 3)
		    		.setParameter("earnerID", 3)
		    		.setParameter("incomeID", 15)
		    		.setParameter("geoID", geoAreaID); 
		  
		
		   //numberOfHouseholds = tempQuery.getResultList().size();
		    List<Household> houses =  tempQuery.getResultList();
		    for(Household h : houses)
		    {
		    	numberOfHouseholds++;
		    	System.out.println(h.getGeographicArea().getName());
		    }


		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

    	
    	
    	return numberOfHouseholds;
    }
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
    
    //returns list of Geo Areaas with details
    public static List<GeographicArea> getGeoDetailsList(Connection tempConnection) throws SQLException
    {
    
        String tempSQLSelectQuery = "";

        PreparedStatement tempPreparedStatement = tempConnection.prepareStatement(tempSQLSelectQuery);
        ResultSet tempResultSet = tempPreparedStatement.executeQuery();
        
        List <GeographicArea> geoDetailList = new ArrayList <GeographicArea>();

        while (tempResultSet.next())
        {
        
        	
        	String altcode = tempResultSet.getString("ALTERNATIVECODE"); 
            String name = tempResultSet.getString("NAME");       
            String code = tempResultSet.getString("CODE"); 
            String level = tempResultSet.getString("LEVEL"); 
            String pop = tempResultSet.getString("POPULATION"); 
            
            
        	GeographicArea ga = new GeographicArea(altcode, name, code, level, pop);
            geoDetailList.add(ga);
     
        }
    	
        return geoDetailList;
    	
    }
    
    //returns age groups
    public static List<AgeGroup> getAgeGroupList(Connection tempConnection) throws SQLException
    {
    	String tempSQLSelectQuery = "SELECT DISTINCT a.censusyear, ag.ageGroupID , ag.DESCRIPTION, SUM(a.MALE) AS MALE, SUM(a.FEMALE) AS FEMALE "+ 
				"FROM AGE a JOIN AGEGROUP ag ON a.AGEGROUP = ag.AGEGROUPID "+
    			"JOIN GEOGRAPHICAREA ga ON a.GEOGRAPHICAREA = ga.GEOGRAPHICAREAID "+
    			"WHERE ga.GEOGRAPHICAREAID = 1 AND ag.ageGroupID "+
    			"IN (3,9,22,28,34,40,46,52,58,70,76,83,89,95,101,108,114,120,126) "+
    			"GROUP BY a.censusyear, ag.DESCRIPTION,  ag.ageGroupID";

        PreparedStatement tempPreparedStatement = tempConnection.prepareStatement(tempSQLSelectQuery);
        ResultSet tempResultSet = tempPreparedStatement.executeQuery();
        
        List <AgeGroup> ageGroup = new ArrayList <AgeGroup>();

        while (tempResultSet.next())
        {        	
        	AgeGroup ag = new AgeGroup(tempResultSet.getString("DESCRIPTION"),tempResultSet.getString("MALE"),tempResultSet.getString("FEMALE"), tempResultSet.getString("CENSUSYEAR"));
        	ageGroup.add(ag);
        }
    	
        return ageGroup;
    	
    	
    }
    
    
	
	
	

}
