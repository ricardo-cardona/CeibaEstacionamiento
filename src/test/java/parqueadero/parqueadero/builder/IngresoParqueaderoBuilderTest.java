package parqueadero.parqueadero.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Test;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.persistencia.builder.IngresoParqueaderoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.IngresoParqueaderoEntityTestDataBuilder;
import testdatabuilder.IngresoParqueaderoTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class IngresoParqueaderoBuilderTest {
	
	private static final double VALOR = 10000;
	private static final double DELTA = 0.00001;
	
	@Test
	public void convertirADominioTest() {
		
		//arrange
		VehiculoEntity vehiculoEntity = new VehiculoEntityTestDataBuilder().build();
		
		IngresoParqueaderoEntity ingresoParqueaderoEntity = new IngresoParqueaderoEntityTestDataBuilder()
				.conVehiculo(vehiculoEntity)
				.conFechaInicio(Calendar.getInstance())
				.conFechaFin(Calendar.getInstance())
				.conValor(VALOR)
				.build();
		
		//act
		IngresoParqueadero ingresoParqueadero = IngresoParqueaderoBuilder.convertirADominio(ingresoParqueaderoEntity);
		
		//assert
		assertEquals(ingresoParqueaderoEntity.getVehiculo().getPlaca(), ingresoParqueadero.getVehiculo().getPlaca());
		assertEquals(ingresoParqueaderoEntity.getFechaInicio(), ingresoParqueadero.getFechaInicio());
		assertEquals(ingresoParqueaderoEntity.getFechaFin(), ingresoParqueadero.getFechaFin());
		assertEquals(ingresoParqueaderoEntity.getValor(), ingresoParqueadero.getValor(), DELTA);
		
	}
	
	@Test
	public void convertirADominioNullTest() {
		
		//arrange
		IngresoParqueaderoEntity ingresoParqueaderoEntity = null;
		
		//act
		IngresoParqueadero ingresoParqueadero = IngresoParqueaderoBuilder.convertirADominio(ingresoParqueaderoEntity);
		
		//assert
		assertNull(ingresoParqueadero);
		
	}
	
	@Test
	public void convertirAEntityTest() {
		
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		IngresoParqueadero ingresoParqueadero = new IngresoParqueaderoTestDataBuilder()
				.conVehiculo(vehiculo)
				.conFechaInicio(Calendar.getInstance())
				.conFechaFin(Calendar.getInstance())
				.conValor(VALOR)
				.build();
		
		//act
		IngresoParqueaderoEntity ingresoParqueaderoEntity = IngresoParqueaderoBuilder.convertirAEntity(ingresoParqueadero);
		
		//assert
		assertEquals(ingresoParqueadero.getVehiculo().getPlaca(), ingresoParqueaderoEntity.getVehiculo().getPlaca());
		assertEquals(ingresoParqueadero.getFechaInicio(), ingresoParqueaderoEntity.getFechaInicio());
		assertEquals(ingresoParqueadero.getFechaFin(), ingresoParqueaderoEntity.getFechaFin());
		assertEquals(ingresoParqueadero.getValor(), ingresoParqueaderoEntity.getValor(), DELTA);
		
	}
	
	@Test
	public void convertirAEntityNullTest() {
		
		//arrange
		IngresoParqueadero ingresoParqueadero = null;
		
		//act
		IngresoParqueaderoEntity ingresoParqueaderoEntity = IngresoParqueaderoBuilder.convertirAEntity(ingresoParqueadero);
		
		//assert
		assertNull(ingresoParqueaderoEntity);
		
	}

}
