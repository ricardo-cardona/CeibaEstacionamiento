package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.Vehiculo;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaDiaHabilPlacaTest {
	
	private static final String PLACA_CON_A = "ABC-123";
	private static final String PLACA_CON_X = "XYZ-789";
	
	/*@Test
	public void PlacaConAEnLunesTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void PlacaConAEnDomingoTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void PlacaConAEnMiercolesTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca();
		
		Calendar calendar = mock(Calendar.class);
		
		calendar.set(2018, Calendar.JULY, 17, 0, 0, 0); // 2018/07/17 (Miércoles)
		
		when(Calendar.getInstance()).thenReturn(calendar);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}*/
	
	@Test
	public void PlacaConX() {

		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_X)
				.build();
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca();
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}

}
