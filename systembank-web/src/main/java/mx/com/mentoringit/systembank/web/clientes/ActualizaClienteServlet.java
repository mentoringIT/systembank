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
import mx.com.mentoringit.systembank.dto.Cliente;

/**
 * Servlet implementation class ActualizaClienteServlet
 */
public class ActualizaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizaClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Actualizar</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(request.getParameter("id")));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellidoPaterno(request.getParameter("apellidoPaterno"));
		cliente.setApellidoMaterno(request.getParameter("apellidoMaterno"));
		cliente.setEdad(Integer.parseInt(request.getParameter("edad")));
		cliente.setBancoId(Integer.parseInt(request.getParameter("bancoId")));
		
		try {
			if(clienteDAO.actualizar(cliente)) {
			
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		writer.println("<h1>Actualizar</h1>");
	}

}
