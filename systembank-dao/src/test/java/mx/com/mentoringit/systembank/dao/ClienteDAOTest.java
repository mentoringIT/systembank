package mx.com.mentoringit.systembank.dao;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;
import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dto.Banco;
import mx.com.mentoringit.systembank.dto.Cliente;

import org.junit.Ignore;
import org.junit.Test;

public class ClienteDAOTest {

	private ClienteDAO clienteDAO = new ClienteDAOImpl();
		
	@Ignore
	@Test
	public void testAdd() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Adrian");
		cliente.setApellidoPaterno("Osorio");
		cliente.setApellidoMaterno("Pulido");
		cliente.setEdad(28);
		cliente.setBancoId(Banco.BANAMEX.getId());
		
		try {
			Assert.assertTrue(clienteDAO.agregar(cliente));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Ignore
	@Test 
	public void testGetAll(){
		List<Cliente> clientes;		
		try {
			clientes = clienteDAO.listarClientes();
			Assert.assertNotNull(clientes);
			Assert.assertTrue(clientes.size() > 0);
			for(Cliente cliente : clientes) {
				Cliente clienteRecuperado;
				clienteRecuperado = 
					clienteDAO.obtenerCliente(cliente.getId());
				Assert.assertNotNull(clienteRecuperado);
				Assert.assertTrue(clienteRecuperado instanceof Cliente);
				clienteRecuperado.setNombre(
					clienteRecuperado.getNombre() + "Editado");
				Assert.assertTrue(clienteDAO.actualizar(clienteRecuperado));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test 
	public void testDeleteAll(){
		List<Cliente> clientes;		
		try {
			clientes = clienteDAO.listarClientes();
			Assert.assertNotNull(clientes);
			Assert.assertTrue(clientes.size() > 0);
			for(Cliente cliente : clientes) {			
				Assert.assertTrue(clienteDAO.borrar(cliente.getId()));
			}
			
			clientes = clienteDAO.listarClientes();
			Assert.assertTrue(clientes.size() == 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
