package mx.com.mentoringit.systembank.dao.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import mx.com.mentoringit.systembank.dto.Movimiento;

public interface MovimientoDAO {

	boolean registrarMovimiento(Movimiento movimiento) throws SQLException;
	
	List<Movimiento> obtenMovimientosCuenta(int cuentaId) throws SQLException;
	
	List<Movimiento> obtenMovimientosFecha(int cuentaId, Date fecha) throws SQLException;
	
}
