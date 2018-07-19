package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import testdatabuilder.IngresoParqueaderoTestDataBuilder;

public class ReglaSalidaRegistradaTest {

	@Test
	public void reglaSalidaRegistradaTest() {

		//arrange
		IngresoParqueadero ingreso = new IngresoParqueaderoTestDataBuilder()
				.conFechaFin(Calendar.getInstance())
				.build(); 
		
		ReglaSalidaRegistrada regla = new ReglaSalidaRegistrada();
		
		//act
		try {
			regla.verificarRegla(ingreso);
		} catch (SalidaParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaSalidaRegistrada.SALIDA_YA_REGISTRADA, e.getMessage());
		}
		
	}

}
