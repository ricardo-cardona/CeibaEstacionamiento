package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.Vehiculo;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaDiaHabilPlacaTest {
	
	private static final String PLACA_CON_A = "ABC-123";
	private static final String PLACA_CON_X = "XYZ-789";
	
	@Test
	public void PlacaConAEnDomingoTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2018, Calendar.JULY, 15, 0, 0, 0); // 2018/07/15 (Domingo)
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca(calendar);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}
	
	@Test
	public void PlacaConAEnLunesTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2018, Calendar.JULY, 16, 0, 0, 0); // 2018/07/16 (Lunes)
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca(calendar);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}
	
	@Test
	public void PlacaConAEnMiercolesTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2018, Calendar.JULY, 18, 0, 0, 0); // 2018/07/18 (Miercoles)
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca(calendar);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertFalse(resultado);
		
	}
	
	@Test
	public void PlacaConX() {

		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_X)
				.build();
		
		ReglaDiaHabilPlaca regla = new ReglaDiaHabilPlaca(Calendar.getInstance());
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}

}
