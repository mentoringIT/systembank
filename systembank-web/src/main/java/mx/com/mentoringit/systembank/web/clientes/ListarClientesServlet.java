package mx.com.mentoringit.systembank.web.clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dto.Cliente;

/**
 * Servlet implementation class ListarClientesServlet
 */
public class ListarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = clienteDAO.listarClientes();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		writer.println("<h1>Listar</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
