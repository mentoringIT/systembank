package mx.com.mentoringit.systembank.web.movimientos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dao.interfaces.MovimientoDAO;
import mx.com.mentoringit.systembank.dto.Cuenta;
import mx.com.mentoringit.systembank.dto.Movimiento;

/**
 * Servlet implementation class ListarMovimientosServlet
 */
public class ListarMovimientosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovimientoDAO movimientoDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarMovimientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		ApplicationContext context =
			WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		movimientoDAO = (MovimientoDAO)context.getBean("MovimientoDAO");
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		int cuentaId = Integer.parseInt(request.getAttribute("cuentaId").toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			movimientos = movimientoDAO.obtenMovimientosCuenta(cuentaId);
			result.put("movimientos", movimientos);
			result.put("total", movimientos.size());
			result.put("success", true);
		} catch (SQLException e) {
			e.printStackTrace();
			result.put("success", false);
		}
	
		Gson convertidor = new Gson();
		writer.println(convertidor.toJson(result));
		writer.close();
	}

}
