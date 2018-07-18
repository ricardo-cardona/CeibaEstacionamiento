package parqueadero.parqueadero.persistencia.entidad.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;

public class TipoVehiculoEntityTest {
	
	private static final Long ID = 2L;
	private static final String NOMBRE = "MOTO";
	private static final int CAPACIDAD_MAXIMA = 10;
	private static final double VALOR_HORA = 500;
	private static final double VALOR_DIA = 4000;
	private static final boolean TIENE_CILINDRAJE = true;
	private static final int ALTO_CILINDRAJE = 500;
	private static final double VALOR_ADICIONAL_CILINDRAJE = 2000;
	
	private static final double DELTA = 0.00001;

	@Test
	public void crearTipoVehiculoEntityTest() {
		
		// arrange
		TipoVehiculoEntityTestDataBuilder tipoVehiculoBuilder = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID)
				.conNombre(NOMBRE)
				.conCapacidadMaxima(CAPACIDAD_MAXIMA)
				.conValorHora(VALOR_HORA)
				.conValorDia(VALOR_DIA)
				.conTieneCilindraje(TIENE_CILINDRAJE)
				.conAltoCilindraje(ALTO_CILINDRAJE)
				.conValorAdicionalCilindraje(VALOR_ADICIONAL_CILINDRAJE);
		
		// act
		TipoVehiculoEntity tipoVehiculo = tipoVehiculoBuilder.build();
		
		// assert
		assertEquals(ID, tipoVehiculo.getId());
		assertEquals(NOMBRE, tipoVehiculo.getNombre());
		assertEquals(CAPACIDAD_MAXIMA, tipoVehiculo.getCapacidadMaxima());
		assertEquals(VALOR_HORA, tipoVehiculo.getValorHora(), DELTA);
		assertEquals(VALOR_DIA, tipoVehiculo.getValorDia(), DELTA);
		assertEquals(TIENE_CILINDRAJE, tipoVehiculo.getTieneCilindraje());
		assertEquals(ALTO_CILINDRAJE, tipoVehiculo.getAltoCilindraje());
		assertEquals(VALOR_ADICIONAL_CILINDRAJE, tipoVehiculo.getValorAdicionalCilindraje(), DELTA);
		
	}

}
