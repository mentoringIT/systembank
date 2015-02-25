package mx.com.mentoringit.systembank.dao;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.Test;

public class ConeccionFactoryTestCase {

	@Test
	public void test() {
		ConnectionFactory instancia1, instancia2;
		instancia1 = ConnectionFactory.getInstancia();
		instancia2 = ConnectionFactory.getInstancia();
		
		Assert.assertNotNull(instancia1);
		Assert.assertNotNull(instancia2);
		Assert.assertTrue(instancia1 instanceof ConnectionFactory);
		Assert.assertEquals(instancia1, instancia2);
		
	}
	
	@Test
	public void testConexion(){
		ConnectionFactory factory;
		Connection conexion;
		factory = ConnectionFactory.getInstancia();
		
		conexion = factory.getConnection();
		Assert.assertNotNull(conexion);
		Assert.assertTrue(conexion instanceof Connection);
		
	}

}
