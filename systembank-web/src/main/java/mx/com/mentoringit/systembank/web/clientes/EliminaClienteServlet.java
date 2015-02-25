package mx.com.mentoringit.systembank.web.clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;

/**
 * Servlet implementation class EliminaClienteServlet
 */
public class EliminaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
//		int clienteId = Integer.parseInt(request.getParameter("id"));
		writer.println("<h1>Eliminar</h1>");
//		try {
//			if(clienteDAO.borrar(clienteId)) {
//
//			}
//		} catch (SQLException e) {
//
//		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
