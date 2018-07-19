package parqueadero.parqueadero.persistencia.entidad.unitaria;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.IngresoParqueaderoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

public class IngresoParqueaderoEntityTest {
	
	private static final Long ID_INGRESO = 1L;
	private static final double VALOR = 4000;
	private static final double DELTA = 0.00001;
	
	private static final Calendar getFechaInicio() {
		
		Calendar fecha = Calendar.getInstance();
		
		fecha.set(2018, 07, 19, 11, 23, 35);
		
		return fecha;
		
	}
	
	private static final Calendar getFechaFin() {
		
		Calendar fecha = Calendar.getInstance();
		
		fecha.set(2018, 07, 19, 15, 19, 42);
		
		return fecha;
		
	}
	
	@Test
	public void crearIngresoParqueaderoTest() {

		// arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		Calendar fechaInicio = getFechaInicio();
		Calendar fechaFin = getFechaFin();
				
		IngresoParqueaderoEntityTestDataBuilder ingresoBuilder = new IngresoParqueaderoEntityTestDataBuilder()
				.conId(ID_INGRESO)
				.conVehiculo(vehiculo)
				.conFechaInicio(fechaInicio)
				.conFechaFin(fechaFin)
				.conValor(VALOR);
		
		// act
		IngresoParqueaderoEntity ingreso = ingresoBuilder.build();
		
		// assert
		assertEquals(ID_INGRESO, ingreso.getId());
		assertEquals(vehiculo.getPlaca(), ingreso.getVehiculo().getPlaca());
		assertEquals(fechaInicio, ingreso.getFechaInicio());
		assertEquals(fechaFin, ingreso.getFechaFin());
		assertEquals(VALOR, ingreso.getValor(), DELTA);
		
	}

}
