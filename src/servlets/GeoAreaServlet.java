//DAVID WAGNER + ERIC TOSSELL
//ASSIGNMENT 1
//CREATED 2/13/2018
//FINISH v.10 2/16/2018
//FINISH v.2 ASSIGNMENT 2 4/6/2018

//SERVLET RUNS GEO AREA LIST

package servlets;

import java.io.IOException;

import java.util.List;
import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import db.DBHandler;
import model.GeographicArea;

/**
 * Servlet implementation class GeoAreaServlet
 */
@WebServlet("/GeoAreaServlet")
public class GeoAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeoAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBHandler db = new DBHandler();
		
		
	
		try {
			db.createConnection();
			
			List<GeographicArea> geoCat0 = db.getGeographicAreaList(0);
			List<GeographicArea> geoCat1 = db.getGeographicAreaList(1);
			List<GeographicArea> geoCat2 = db.getGeographicAreaList(2);
			List<GeographicArea> geoCat3 = db.getGeographicAreaList(3);
			
			request.setAttribute("geoCat0", geoCat0);
			request.setAttribute("geoCat1", geoCat1);
			request.setAttribute("geoCat2", geoCat2);
			request.setAttribute("geoCat3", geoCat3);
			
			
	
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	
		

		RequestDispatcher rd= request.getRequestDispatcher("./geoAreaList.jsp");
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
