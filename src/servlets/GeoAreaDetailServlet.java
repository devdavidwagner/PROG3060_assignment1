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

import model.Age;
import model.GeographicArea;
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
		
		try {
			db.createConnection();
			
			int geoAreaID = Integer.parseInt(request.getParameter("geoAreaID"));
		
			List<Age> ages = db.getAgeByGeoAreaID(geoAreaID);
			Integer numberHouseholdsWithin = 0;
			GeographicArea geoArea = ages.get(0).getGeoArea();
			List<String> geoAreasWithin = db.getAreasWithin(geoArea.getLevel(), geoArea.getAltCode());
			
		
			System.out.println("GEOGRAPHIC AREA: " + geoArea.getName());
			if(geoArea.getLevel() == 0 || geoArea.getLevel() == 1)
			{
				numberHouseholdsWithin = db.totalHouseholds(geoArea.getGeoAreaID());
			}
			
			
			request.setAttribute("age", ages);
			request.setAttribute("geoArea", geoArea);
			request.setAttribute("numberHouseholdsWithin", numberHouseholdsWithin);
			request.setAttribute("geoAreasWithin", geoAreasWithin);
	
			
	
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
