package testdatabuilder;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private String placa;
	private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	
	public VehiculoTestDataBuilder() {
		this.placa = "XYZ-789";
		this.tipoVehiculo = new TipoVehiculoTestDataBuilder().build();
		this.cilindraje = 0;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.tipoVehiculo, this.cilindraje);
	}
	
}
