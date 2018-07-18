package parqueadero.parqueadero.persistencia.entidad.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

public class VehiculoEntityTest {
	
	private static final String PLACA = "ABC-123";
	private static final int CILINDRAJE = 0;

	@Test
	public void crearVehiculoTest() {
		
		// arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder().build();
				
		VehiculoEntityTestDataBuilder vehiculoBuilder = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(CILINDRAJE);
		
		// act
		VehiculoEntity vehiculo = vehiculoBuilder.build();
		
		// assert
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(tipoVehiculo.getId(), vehiculo.getTipoVehiculo().getId());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		
	}

}
