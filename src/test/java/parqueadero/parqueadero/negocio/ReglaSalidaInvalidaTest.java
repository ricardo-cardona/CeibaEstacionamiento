package parqueadero.parqueadero.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;

public class ReglaSalidaInvalidaTest {

	@Test
	public void reglaSalidaInvalidaTest() {
		
		//arrange
		IngresoParqueadero ingreso = null;
		
		ReglaSalidaInvalida regla = new ReglaSalidaInvalida();
		
		//act
		try {
			regla.verificarRegla(ingreso);
			fail();
		} catch (SalidaParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaSalidaInvalida.SALIDA_INVALIDA, e.getMessage());
		}
		
	}

}
