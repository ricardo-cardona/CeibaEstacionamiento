package parqueadero.parqueadero.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.persistencia.builder.TipoVehiculoBuilder;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.TipoVehiculoTestDataBuilder;

public class TipoVehiculoBuilderTest {
	
	private static final Long ID = 3L;
	private static final String NOMBRE = "PRUEBA";
	private static final int CAPACIDAD_MAXIMA = 50;
	private static final double VALOR_HORA = 3000;
	private static final double VALOR_DIA = 20000;
	private static final boolean TIENE_CILINDRAJE = true;
	private static final int ALTO_CILINDRAJE = 250;
	private static final double VALOR_ADICIONAL_CILINDRAJE = 5000;
	
	private static final double DELTA = 0.00001;
	
	@Test
	public void convertirADominioTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID)
				.conNombre(NOMBRE)
				.conCapacidadMaxima(CAPACIDAD_MAXIMA)
				.conValorHora(VALOR_HORA)
				.conValorDia(VALOR_DIA)
				.conTieneCilindraje(TIENE_CILINDRAJE)
				.conAltoCilindraje(ALTO_CILINDRAJE)
				.conValorAdicionalCilindraje(VALOR_ADICIONAL_CILINDRAJE)
				.build();
		
		//act
		TipoVehiculo tipoVehiculo = TipoVehiculoBuilder.convertirADominio(tipoVehiculoEntity);
		
		//assert
		assertEquals(tipoVehiculoEntity.getId(), tipoVehiculo.getId());
		assertEquals(tipoVehiculoEntity.getNombre(), tipoVehiculo.getNombre());
		assertEquals(tipoVehiculoEntity.getCapacidadMaxima(), tipoVehiculo.getCapacidadMaxima());
		assertEquals(tipoVehiculoEntity.getValorHora(), tipoVehiculo.getValorHora(), DELTA);
		assertEquals(tipoVehiculoEntity.getValorDia(), tipoVehiculo.getValorDia(), DELTA);
		assertEquals(tipoVehiculoEntity.getTieneCilindraje(), tipoVehiculo.getTieneCilindraje());
		assertEquals(tipoVehiculoEntity.getAltoCilindraje(), tipoVehiculo.getAltoCilindraje());
		assertEquals(tipoVehiculoEntity.getValorAdicionalCilindraje(), tipoVehiculo.getValorAdicionalCilindraje(), DELTA);
		
	}
	
	@Test
	public void convertirADominioNullTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculoEntity = null;
		
		//act
		TipoVehiculo tipoVehiculo = TipoVehiculoBuilder.convertirADominio(tipoVehiculoEntity);
		
		//assert
		assertNull(tipoVehiculo);
		
	}
	
	@Test
	public void convertirAEntityTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conId(ID)
				.conNombre(NOMBRE)
				.conCapacidadMaxima(CAPACIDAD_MAXIMA)
				.conValorHora(VALOR_HORA)
				.conValorDia(VALOR_DIA)
				.conTieneCilindraje(TIENE_CILINDRAJE)
				.conAltoCilindraje(ALTO_CILINDRAJE)
				.conValorAdicionalCilindraje(VALOR_ADICIONAL_CILINDRAJE)
				.build();
		
		//act
		TipoVehiculoEntity tipoVehiculoEntity = TipoVehiculoBuilder.convertirAEntity(tipoVehiculo);
		
		//assert
		assertEquals(tipoVehiculo.getId(), tipoVehiculoEntity.getId());
		assertEquals(tipoVehiculo.getNombre(), tipoVehiculoEntity.getNombre());
		assertEquals(tipoVehiculo.getCapacidadMaxima(), tipoVehiculoEntity.getCapacidadMaxima());
		assertEquals(tipoVehiculo.getValorHora(), tipoVehiculoEntity.getValorHora(), DELTA);
		assertEquals(tipoVehiculo.getValorDia(), tipoVehiculoEntity.getValorDia(), DELTA);
		assertEquals(tipoVehiculo.getTieneCilindraje(), tipoVehiculoEntity.getTieneCilindraje());
		assertEquals(tipoVehiculo.getAltoCilindraje(), tipoVehiculoEntity.getAltoCilindraje());
		assertEquals(tipoVehiculo.getValorAdicionalCilindraje(), tipoVehiculoEntity.getValorAdicionalCilindraje(), DELTA);
		
	}
	
	@Test
	public void convertirAEntityNullTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = null;
		
		//act
		TipoVehiculoEntity tipoVehiculoEntity = TipoVehiculoBuilder.convertirAEntity(tipoVehiculo);
		
		//assert
		assertNull(tipoVehiculoEntity);
		
	}

}
