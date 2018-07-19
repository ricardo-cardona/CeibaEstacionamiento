package parqueadero.parqueadero.dominio.unitaria;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.dominio.Vehiculo;
import testdatabuilder.IngresoParqueaderoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class IngresoParqueaderoTest {
	
	private static final Long ID_INGRESO = 1L;
	private static final double VALOR = 5000;
	private static final double DELTA = 0.00001;

	@Test
	public void crearIngresoParqueaderoTest() {
		
		// arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
				
		IngresoParqueaderoTestDataBuilder ingresoBuilder = new IngresoParqueaderoTestDataBuilder()
				.conId(ID_INGRESO)
				.conVehiculo(vehiculo)
				.conFechaInicio(fechaInicio)
				.conFechaFin(fechaFin)
				.conValor(VALOR);
		
		// act
		IngresoParqueadero ingreso = ingresoBuilder.build();
		
		// assert
		assertEquals(ID_INGRESO, ingreso.getId());
		assertEquals(vehiculo.getPlaca(), ingreso.getVehiculo().getPlaca());
		assertEquals(fechaInicio, ingreso.getFechaInicio());
		assertEquals(fechaFin, ingreso.getFechaFin());
		assertEquals(VALOR, ingreso.getValor(), DELTA);
		
	}

}
