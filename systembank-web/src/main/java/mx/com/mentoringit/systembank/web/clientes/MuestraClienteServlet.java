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
import mx.com.mentoringit.systembank.dto.Banco;
import mx.com.mentoringit.systembank.dto.Cliente;

/**
 * Servlet implementation class MuestraClienteServlet
 */
public class MuestraClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MuestraClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
//		int clienteId = Integer.parseInt(request.getParameter("id"));
//		try {
//			Cliente cliente = clienteDAO.obtenerCliente(clienteId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		writer.println("<h1>Muestra Cliente</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
