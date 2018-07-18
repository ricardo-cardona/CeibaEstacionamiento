package parqueadero.parqueadero.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;
import testdatabuilder.TipoVehiculoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class ReglaCilindrajeTest {
	
	private static final int CON_CILINDRAJE = 100;
	private static final int SIN_CILINDRAJE = 0;
	
	@Test
	public void VehiculoSinCilindrajeTipoVehiculoNoRequiereCilindrajeTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conTieneCilindraje(false)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conCilindraje(SIN_CILINDRAJE)
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		ReglaCilindraje reglaCilindraje = new ReglaCilindraje();
		
		//act
		boolean resultado = reglaCilindraje.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}

	@Test
	public void VehiculoConCilindrajeTipoVehiculoRequiereCilindrajeTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conTieneCilindraje(true)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conCilindraje(CON_CILINDRAJE)
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		ReglaCilindraje reglaCilindraje = new ReglaCilindraje();
		
		//act
		boolean resultado = reglaCilindraje.verificarRegla(vehiculo);
		
		//assert
		assertTrue(resultado);
		
	}

	@Test
	public void VehiculoConCilindrajeTipoVehiculoNoRequiereCilindrajeTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conTieneCilindraje(false)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conCilindraje(CON_CILINDRAJE)
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		ReglaCilindraje reglaCilindraje = new ReglaCilindraje();
		
		//act
		boolean resultado = reglaCilindraje.verificarRegla(vehiculo);
		
		//assert
		assertFalse(resultado);
		
	}

	@Test
	public void VehiculoSinCilindrajeTipoVehiculoRequiereCilindrajeTest() {
		
		//arrange
		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder()
				.conTieneCilindraje(true)
				.build();
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conCilindraje(SIN_CILINDRAJE)
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		ReglaCilindraje reglaCilindraje = new ReglaCilindraje();
		
		//act
		boolean resultado = reglaCilindraje.verificarRegla(vehiculo);
		
		//assert
		assertFalse(resultado);
		
	}

}
