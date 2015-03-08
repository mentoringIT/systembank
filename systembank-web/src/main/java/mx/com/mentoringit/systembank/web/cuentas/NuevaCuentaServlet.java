package mx.com.mentoringit.systembank.web.cuentas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cuenta;

/**
 * Servlet implementation class NuevaCuentaServlet
 */
public class NuevaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuentaDAO cuentaDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevaCuentaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Nuevo</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		cuentaDAO = (CuentaDAO)context.getBean("CuentaDAO");
		
		Cuenta cuenta = new Cuenta();
		cuenta.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		cuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("idTipoCuenta")));
		cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("numeroCuenta").toString()));
		cuenta.setSaldo(Double.parseDouble(request.getParameter("saldo")));		
		System.out.println(cuenta);
		Map<String, Object> result = new HashMap<String, Object>(); 
		
		try {
			if(cuentaDAO.agregar(cuenta)) {
				result.put("success", true);
			}
		} catch (SQLException e) {
			result.put("success", false);
			result.put("error", e.getMessage());
			e.printStackTrace();
		}
		Gson gson = new Gson();
		writer.println(gson.toJson(result));
		
	}

}
