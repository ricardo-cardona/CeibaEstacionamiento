package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaPlacaTest {
	
	private static final String PLACA_VACIA = "";
	private static final String PLACA_CON_A = "ABC-123";
	private static final String PLACA_CON_X = "XYZ-789";
	
	@Test
	public void sinPlacaTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_VACIA)
				.build();
		
		ReglaPlaca regla = new ReglaPlaca(Calendar.getInstance());
		
		//act
		try {
			regla.verificarRegla(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaPlaca.SIN_PLACA, e.getMessage());
		}
		
	}
	
	@Test
	public void PlacaConAEnLunesTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2018, Calendar.JULY, 16, 0, 0, 0); // 2018/07/16 (Lunes)
		
		ReglaPlaca regla = new ReglaPlaca(calendar);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
		} catch(IngresoParqueaderoExcepcion e) {
			fail();
		}
		
		//assert
		assertTrue(true);
		
	}
	
	@Test
	public void PlacaConAEnMiercolesTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_A)
				.build();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2018, Calendar.JULY, 18, 0, 0, 0); // 2018/07/18 (Miercoles)
		
		ReglaPlaca regla = new ReglaPlaca(calendar);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaPlaca.DIA_NO_HABIL_PLACA, e.getMessage());
		}
		
	}
	
	@Test
	public void PlacaConX() {

		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA_CON_X)
				.build();
		
		ReglaPlaca regla = new ReglaPlaca(Calendar.getInstance());
		
		//act
		try {
			regla.verificarRegla(vehiculo);
		} catch(IngresoParqueaderoExcepcion e) {
			fail();
		}
		
		//assert
		assertTrue(true);
		
	}

}
