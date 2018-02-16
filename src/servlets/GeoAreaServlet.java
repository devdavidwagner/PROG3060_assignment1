//DAVID WAGNER + ERIC TOSSELL
//ASSIGNMENT 1
//CREATED 2/13/2018
//FINISH v.10 2/16/2018

//SERVLET RUNS GEO AREA LIST

package servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import db.DBHandler;

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
		
		Connection conn;
		try {
			conn = db.createConnection();
			List <String> geoCat0 = DBHandler.getGeoAreaList(conn,0);
			List <String> geoCat1 = DBHandler.getGeoAreaList(conn,1);
			List <String> geoCat2 = DBHandler.getGeoAreaList(conn,2);
			List <String> geoCat3 = DBHandler.getGeoAreaList(conn,3);
			
			request.setAttribute("Cat0", geoCat0);
			request.setAttribute("Cat1", geoCat1);
			request.setAttribute("Cat2", geoCat2);
			request.setAttribute("Cat3", geoCat3);
	
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
