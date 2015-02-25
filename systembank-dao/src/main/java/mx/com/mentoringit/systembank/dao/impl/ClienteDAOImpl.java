package mx.com.mentoringit.systembank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.mentoringit.systembank.dao.ConnectionFactory;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dto.Banco;
import mx.com.mentoringit.systembank.dto.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private Connection conexion;
	
	public boolean agregar(Cliente cliente) throws SQLException {

		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String insert = "INSERT INTO cliente "
				+ "(nombre, apaterno, amaterno, edad, idBanco) "
				+ "values (?, ?, ?, ?, ?)";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(insert);
		
		sentencia.setString(1, cliente.getNombre());
		sentencia.setString(2, cliente.getApellidoPaterno());
		sentencia.setString(3, cliente.getApellidoMaterno());
		sentencia.setInt(4, cliente.getEdad());
		sentencia.setInt(5, cliente.getBancoId());
		
		
		if(!sentencia.execute()) {
			result = (sentencia.getUpdateCount() > 0);
		}
		
		ConnectionFactory.getInstancia().closeConnection();
		return result;
	}

	public boolean borrar(int clienteId) throws SQLException {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String delete = "DELETE FROM cliente "
				+ "WHERE idCliente = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(delete);
		sentencia.setInt(1, clienteId);
		if(!sentencia.execute()) {
			result = (sentencia.getUpdateCount() > 0);
		}
		ConnectionFactory.getInstancia().closeConnection();
		return result;
	}

	public boolean actualizar(Cliente cliente) throws SQLException {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String update = "UPDATE cliente "
				+ "SET nombre = ?, "
				+ "apaterno = ?, "
				+ "amaterno = ?, "
				+ "edad = ?, "
				+ "idBanco = ? "
				+ "WHERE idCliente = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(update);
		
		sentencia.setString(1, cliente.getNombre());
		sentencia.setString(2, cliente.getApellidoPaterno());
		sentencia.setString(3, cliente.getApellidoMaterno());
		sentencia.setInt(4, cliente.getEdad());
		sentencia.setInt(5, cliente.getBancoId());
		sentencia.setInt(6, cliente.getId());
		
		if(!sentencia.execute()) {
			result = (sentencia.getUpdateCount() > 0);
		}
		
		ConnectionFactory.getInstancia().closeConnection();
		return result;
	}

	public Cliente obtenerCliente(int clienteId) throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM cliente "				
				+ "WHERE idCliente = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);	
		sentencia.setInt(1, clienteId);
		resultSet = sentencia.executeQuery();
		
		while(resultSet.next()){
			Cliente cliente = new Cliente();
			cliente.setId(resultSet.getInt("idCliente"));
			cliente.setNombre(resultSet.getString("nombre"));
			cliente.setApellidoPaterno(resultSet.getString("apaterno"));
			cliente.setApellidoMaterno(resultSet.getString("amaterno"));
			cliente.setEdad(resultSet.getInt("edad"));
			cliente.setBancoId(resultSet.getInt("idBanco"));
			cliente.setBanco(Banco.valueOf(resultSet.getInt("idBanco")));
			return cliente;
		}
		
		return null;
	}

	public List<Cliente> listarClientes() throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM cliente";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);		
		resultSet = sentencia.executeQuery();
		List<Cliente> clientes = new ArrayList<Cliente>();
		while(resultSet.next()){
			Cliente cliente = new Cliente();
			cliente.setId(resultSet.getInt("idCliente"));
			cliente.setNombre(resultSet.getString("nombre"));
			cliente.setApellidoPaterno(resultSet.getString("apaterno"));
			cliente.setApellidoMaterno(resultSet.getString("amaterno"));
			cliente.setEdad(resultSet.getInt("edad"));
			cliente.setBancoId(resultSet.getInt("idBanco"));
			cliente.setBanco(Banco.valueOf(resultSet.getInt("idBanco")));
			clientes.add(cliente);
		}
		return clientes;
	}

}
