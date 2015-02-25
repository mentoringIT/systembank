package mx.com.mentoringit.systembank.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mx.com.mentoringit.systembank.dto.Cuenta;

public interface CuentaDAO {

	boolean agregar(Cuenta cuenta) throws SQLException;
	
	boolean borrar(int cuentaId) throws SQLException;
	
	Cuenta obtenerCuenta(int cuenta) throws SQLException;
	
	List<Cuenta> obtenerCuentasCliente(int clienteId) throws SQLException;
	
}
