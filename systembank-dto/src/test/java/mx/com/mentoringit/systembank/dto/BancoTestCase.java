package mx.com.mentoringit.systembank.dto;

import junit.framework.Assert;
import mx.com.mentoringit.systembank.dto.Banco;

import org.junit.Test;

public class BancoTestCase {

	@Test
	public void test() {
		int bancoId;
		Banco banco;
		
		banco = Banco.BANAMEX;
		Assert.assertNotNull(banco);
		
		bancoId = 1;	
		banco = Banco.valueOf(bancoId);
		Assert.assertNotNull(banco);
		
		bancoId = 10;	
		banco = Banco.valueOf(bancoId);
		Assert.assertNull(banco);
		
		banco = Banco.valueOf("BANAMEX");
		Assert.assertNotNull(banco);
		
	}

}
