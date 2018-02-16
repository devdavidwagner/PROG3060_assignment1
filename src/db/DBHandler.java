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

import model.AgeGroup;
import model.GeoArea;


public class DBHandler {
	
	private static String dbURL = "jdbc:derby://localhost:1527/G:/CanadaCensusDB";
    private static Connection conn = null;
    
    public static final String CONNECTION_USER = "dwet";
    public static final String CONNECTION_PASSWORD = "password123";
    
	
	public static void main(String[] args)
	{
		
	}
	
	//creates new db connection to derby
	public Connection createConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
			

        Properties tempConnProp = new Properties();
        tempConnProp.put("user", CONNECTION_USER);
        tempConnProp.put("password", CONNECTION_PASSWORD);
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection tempConn = DriverManager.getConnection(dbURL, tempConnProp);
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        tempConn.setAutoCommit(false);
        tempConn.createStatement().executeUpdate("SET SCHEMA APP");

	
	     return tempConn;
		
	}
	
	//shutsdown connection to derby database
	private static void closeConnection() {
		
		try {
			
			 DriverManager.getConnection(dbURL + ";shutdown=true");
             conn.close();
			
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
    private static Statement stmt = null;
    
    //gets all data from HHE table
    public static List<String> getGeoAreaList(Connection tempConnection, int level) throws SQLException
    {
    	String GEO_AREA_TABLE = "GEOGRAPHICAREA"; 
        String tempSQLSelectQuery = "SELECT NAME from " + GEO_AREA_TABLE + " WHERE LEVEL = ? ORDER BY LEVEL";
             

        PreparedStatement tempPreparedStatement = tempConnection.prepareStatement(tempSQLSelectQuery);
        tempPreparedStatement.setString(1, Integer.toString(level));
        ResultSet tempResultSet = tempPreparedStatement.executeQuery();
        
        List <String> geoAreaList = new ArrayList <String>();

        while (tempResultSet.next())
        {
   
            String name = tempResultSet.getString("NAME");           
            geoAreaList.add(name);

        }
    	
        return geoAreaList;
    	
    }
    
    //returns list of Geo Areaas with details
    public static List<GeoArea> getGeoDetailsList(Connection tempConnection) throws SQLException
    {
    
        String tempSQLSelectQuery = "SELECT ga.ALTERNATIVECODE, ga.NAME, ga.CODE, ga.LEVEL, (SELECT SUM(MALE + FEMALE)"+
        " FROM AGE a JOIN CENSUSYEAR c ON a.CENSUSYEAR = c.CENSUSYEARID JOIN AGEGROUP ag ON a.AGEGROUP = ag.AGEGROUPID " + 
        		"WHERE a.GEOGRAPHICAREA = ga.GEOGRAPHICAREAID AND c.CENSUSYEARID = 2 AND AGEGROUPID = 1)" 
        		+ "AS population FROM GEOGRAPHICAREA ga INNER JOIN AGE a ON ga.GEOGRAPHICAREAID = a.GEOGRAPHICAREA"+
        		" JOIN CENSUSYEAR c ON a.CENSUSYEAR = c.CENSUSYEARID JOIN AGEGROUP ag ON a.AGEGROUP = ag.AGEGROUPID"+
        		" WHERE c.CENSUSYEARID = 2 AND AGEGROUPID = 1 ORDER BY ga.ALTERNATIVECODE";

        PreparedStatement tempPreparedStatement = tempConnection.prepareStatement(tempSQLSelectQuery);
        ResultSet tempResultSet = tempPreparedStatement.executeQuery();
        
        List <GeoArea> geoDetailList = new ArrayList <GeoArea>();

        while (tempResultSet.next())
        {
        
        	
        	String altcode = tempResultSet.getString("ALTERNATIVECODE"); 
            String name = tempResultSet.getString("NAME");       
            String code = tempResultSet.getString("CODE"); 
            String level = tempResultSet.getString("LEVEL"); 
            String pop = tempResultSet.getString("POPULATION"); 
            
            
        	GeoArea ga = new GeoArea(altcode, name, code, level, pop);
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
