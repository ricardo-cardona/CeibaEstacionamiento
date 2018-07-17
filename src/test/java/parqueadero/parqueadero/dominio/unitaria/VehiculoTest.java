package parqueadero.parqueadero.dominio.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;
import testdatabuilder.TipoVehiculoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {
	
	private static final String PLACA = "ABC-123";
	private static final int CILINDRAJE = 0;

	@Test
	public void crearVehiculoTest() {
		
		// arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder().build();
				
		VehiculoTestDataBuilder vehiculoBuilder = new VehiculoTestDataBuilder()
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(CILINDRAJE);
		
		// act
		Vehiculo vehiculo = vehiculoBuilder.build();
		
		// assert
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(tipoVehiculo.getId(), vehiculo.getTipoVehiculo().getId());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		
	}

}
