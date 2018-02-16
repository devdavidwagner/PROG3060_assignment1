//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018

//SERVLET RUNS GEO AREA DETAILS 


package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GeoArea;
import db.DBHandler;

/**
 * Servlet implementation class GeoAreaDetailServlet
 */
@WebServlet("/GeoAreaDetailServlet")
public class GeoAreaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeoAreaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBHandler db = new DBHandler();
		
		Connection conn;
		try {
			conn = db.createConnection();
			List <GeoArea> geoAreaDetails = DBHandler.getGeoDetailsList(conn);
			
			
			
			
			for(GeoArea element : geoAreaDetails){
				String level = element.getLevel();
				String altCode = element.getAltCode();
				String code = element.getCode();
				
				if(level.equals("0")){
					List<String> areasWithin = new ArrayList<String>();
					
					
					for(GeoArea subElement : geoAreaDetails){
						
						String subLevel = subElement.getLevel();
						
						if(subLevel.equals("1"))
						{
							areasWithin.add(subElement.getName());
						}
						
						
					}
					
					element.setAreasWithin(areasWithin);
				}
				else if(level.equals("1"))
				{
					List<String> areasWithin = new ArrayList<String>();
					
					for(GeoArea subElement : geoAreaDetails){
						String subLevel = subElement.getLevel();
						
						if(subLevel.equals("2")){
							
							String subAltCode = subElement.getAltCode();
							String splitSubAltCode = subAltCode.substring(0,2);
							if(splitSubAltCode.equals(altCode))
							{
								areasWithin.add(subElement.getName());
							}
						}
						
						
					}
					
					element.setAreasWithin(areasWithin);
				}
				else if(level.equals("2"))
				{
					List<String> areasWithin = new ArrayList<String>();
					
					for(GeoArea subElement : geoAreaDetails){
						String subLevel = subElement.getLevel();
						
						if(subLevel.equals("3")){
							
							String altCodeSplit = element.getCode();
							String subAltCode = subElement.getCode().substring(2,5);
																
							if(subAltCode.equals(altCodeSplit))
							{
								areasWithin.add(subElement.getName());
							}
						}
						
						
					}
					
					element.setAreasWithin(areasWithin);
				}
				
				
			}
			
			request.setAttribute("geoArea", geoAreaDetails);
			
			
	

		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		RequestDispatcher rd= request.getRequestDispatcher("./geoAreaDetails.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
