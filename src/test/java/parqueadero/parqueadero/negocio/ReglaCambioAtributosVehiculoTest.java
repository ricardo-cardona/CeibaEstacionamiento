package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.servicio.VehiculoServicio;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.TipoVehiculoTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaCambioAtributosVehiculoTest {
	
	private static final Long TIPO_VEHICULO_1 = 1L;
	private static final Long TIPO_VEHICULO_2 = 2L;
	private static final int CILINDRAJE_125 = 125;
	private static final int CILINDRAJE_250 = 250;
	
	@Test
	public void vehiculoNoRegistradoTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaCambioAtributosVehiculo regla = new ReglaCambioAtributosVehiculo(vehiculoServicio);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		when(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca())).thenReturn(null);
		
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
	public void cambioTipoVehiculoTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaCambioAtributosVehiculo regla = new ReglaCambioAtributosVehiculo(vehiculoServicio);
		
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conId(TIPO_VEHICULO_1)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntityTestDataBuilder()
				.conId(TIPO_VEHICULO_2)
				.build();
		
		VehiculoEntity vehiculoEntity = new VehiculoEntityTestDataBuilder()
				.conTipoVehiculo(tipoVehiculoEntity)
				.build();
		
		when(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca())).thenReturn(vehiculoEntity);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaCambioAtributosVehiculo.CAMBIO_TIPO_VEHICULO, e.getMessage());
		}
		
	}

	@Test
	public void cambioCilindrajeTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaCambioAtributosVehiculo regla = new ReglaCambioAtributosVehiculo(vehiculoServicio);
		
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conId(TIPO_VEHICULO_2)
				.conTieneCilindraje(true)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(CILINDRAJE_250)
				.build();
		
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntityTestDataBuilder()
				.conId(TIPO_VEHICULO_2)
				.conTieneCilindraje(true)
				.build();
		
		VehiculoEntity vehiculoEntity = new VehiculoEntityTestDataBuilder()
				.conTipoVehiculo(tipoVehiculoEntity)
				.conCilindraje(CILINDRAJE_125)
				.build();
		
		when(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca())).thenReturn(vehiculoEntity);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaCambioAtributosVehiculo.CAMBIO_CILINDRAJE, e.getMessage());
		}
		
	}

	@Test
	public void noCambiaronAtributosTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaCambioAtributosVehiculo regla = new ReglaCambioAtributosVehiculo(vehiculoServicio);
		
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conId(TIPO_VEHICULO_2)
				.conTieneCilindraje(true)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(CILINDRAJE_250)
				.build();
		
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntityTestDataBuilder()
				.conId(TIPO_VEHICULO_2)
				.conTieneCilindraje(true)
				.build();
		
		VehiculoEntity vehiculoEntity = new VehiculoEntityTestDataBuilder()
				.conTipoVehiculo(tipoVehiculoEntity)
				.conCilindraje(CILINDRAJE_250)
				.build();
		
		when(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca())).thenReturn(vehiculoEntity);
		
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
