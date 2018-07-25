package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import testdatabuilder.TipoVehiculoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaParqueaderoLlenoTest {
	
	private static final int CAPACIDAD_MAXIMA = 20;
	private static final int DENTRO_DE_LA_CAPACIDAD = 15;
	
	@Test
	public void parqueaderoLlenoTest() {
		
		//arrange
		IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio = mock(IngresoParqueaderoRepositorio.class);
		
		ReglaParqueaderoLleno regla = new ReglaParqueaderoLleno(ingresoParqueaderoRepositorio);
		
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conCapacidadMaxima(CAPACIDAD_MAXIMA)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		when(ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(vehiculo.getTipoVehiculo().getId())).thenReturn(CAPACIDAD_MAXIMA);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaParqueaderoLleno.PARQUEADERO_LLENO, e.getMessage());
		}
		
	}

	@Test
	public void parqueaderoConCupoTest() {
		
		//arrange
		IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio = mock(IngresoParqueaderoRepositorio.class);
		
		ReglaParqueaderoLleno regla = new ReglaParqueaderoLleno(ingresoParqueaderoRepositorio);
		
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conCapacidadMaxima(CAPACIDAD_MAXIMA)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		when(ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(vehiculo.getTipoVehiculo().getId())).thenReturn(DENTRO_DE_LA_CAPACIDAD);
		
		//act
		try {
			regla.verificarRegla(vehiculo);
		} catch(IngresoParqueaderoExcepcion e) {
			//fail
			fail();
		}
		
		//assert
		assertTrue(true);
		
	}

}
