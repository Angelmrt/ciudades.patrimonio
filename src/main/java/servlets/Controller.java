package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCiudad;
import dao.DaoPunto;
import dao.DaoRuta;
import entidades.Ciudad;
import entidades.Punto;
import entidades.Ruta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String operacion = request.getParameter("operacion");
		HttpSession session = request.getSession();
	
		switch (operacion) {
		case "iniciar": {
			List<Ciudad> listaciudades = new ArrayList<Ciudad>();
			DaoCiudad DaoCiudad = new DaoCiudad();
			try {
				listaciudades = DaoCiudad.listadoCiudades();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("listaciudades", listaciudades);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		break;
		}
		case "getruta":{
			int idciudad = Integer.parseInt(request.getParameter("ciudad"));
        	session.setAttribute("ciudad", idciudad);
        	
        	List<Ruta> listarutas = new ArrayList<Ruta>();
        	DaoRuta DaoRuta = new DaoRuta();
        	
        	try {
				listarutas = DaoRuta.findRutasByCiudad(idciudad);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	request.setAttribute("listarutas", listarutas);
			request.getRequestDispatcher("rutas.jsp").forward(request, response);
			break;
		}
		case "rating":{
			int rating = Integer.parseInt(request.getParameter("rating"));
			int rutaID = Integer.parseInt(request.getParameter("rutaid"));
			
			DaoPunto DaoPunto = new DaoPunto();
			Punto punto = new Punto();
			punto.setRuta(rutaID);
			punto.setPuntos(rating);
			try {
				DaoPunto.insertaPunto(punto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("rutas.jsp").forward(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
