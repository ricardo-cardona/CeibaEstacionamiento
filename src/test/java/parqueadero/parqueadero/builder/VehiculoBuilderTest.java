package parqueadero.parqueadero.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.TipoVehiculoTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoBuilderTest {
	
	private static final String PLACA = "XYZ-789";
	private static final Long TIPO_VEHICULO = 9L;
	private static final int CILINDRAJE = 0;
	
	@Test
	public void convertirADominioTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntityTestDataBuilder()
				.conId(TIPO_VEHICULO)
				.build();
		
		VehiculoEntity vehiculoEntity = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculoEntity)
				.conCilindraje(CILINDRAJE)
				.build();
		
		//act
		Vehiculo vehiculo = VehiculoBuilder.convertirADominio(vehiculoEntity);
		
		//assert
		assertEquals(vehiculoEntity.getPlaca(), vehiculo.getPlaca());
		assertEquals(vehiculoEntity.getTipoVehiculo().getId(), vehiculo.getTipoVehiculo().getId());
		assertEquals(vehiculoEntity.getCilindraje(), vehiculo.getCilindraje());
		
	}
	
	@Test
	public void convertirAEntityTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conId(TIPO_VEHICULO)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(CILINDRAJE)
				.build();
		
		//act
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		
		//assert
		assertEquals(vehiculo.getPlaca(), vehiculoEntity.getPlaca());
		assertEquals(vehiculo.getTipoVehiculo().getId(), vehiculoEntity.getTipoVehiculo().getId());
		assertEquals(vehiculo.getCilindraje(), vehiculoEntity.getCilindraje());
		
	}

}
