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
