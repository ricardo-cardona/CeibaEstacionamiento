package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.servicio.VehiculoServicio;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaVehiculoEnParqueaderoTest {

	@Test
	public void vehiculoEstaEnParqueaderoTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaVehiculoEnParqueadero regla = new ReglaVehiculoEnParqueadero(vehiculoServicio);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		VehiculoEntity vehiculoEntity = mock(VehiculoEntity.class);
		
		when(vehiculoServicio.vehiculoEstaEnParqueadero(vehiculo.getPlaca())).thenReturn(vehiculoEntity);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertFalse(resultado);
		
	}

	@Test
	public void vehiculoNoEstaEnParqueaderoTest() {
		
		//arrange
		VehiculoServicio vehiculoServicio = mock(VehiculoServicio.class);
		
		ReglaVehiculoEnParqueadero regla = new ReglaVehiculoEnParqueadero(vehiculoServicio);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		when(vehiculoServicio.vehiculoEstaEnParqueadero(vehiculo.getPlaca())).thenReturn(null);
		
		//act
		boolean resultado = regla.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}

}
