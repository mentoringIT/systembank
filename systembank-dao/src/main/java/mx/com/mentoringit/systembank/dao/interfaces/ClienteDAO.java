package mx.com.mentoringit.systembank.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mx.com.mentoringit.systembank.dto.Cliente;

public interface ClienteDAO {

	boolean agregar(Cliente cliente) throws SQLException;
	
	boolean borrar(int clienteId) throws SQLException;
	
	boolean actualizar(Cliente cliente) throws SQLException;
	
	Cliente obtenerCliente(int clienteId) throws SQLException;
	
	List<Cliente> listarClientes() throws SQLException;
	
}
