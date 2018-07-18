package testdatabuilder;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoEntityTestDataBuilder {
	
	private String placa;
	private TipoVehiculoEntity tipoVehiculo;
	private int cilindraje;
	
	public VehiculoEntityTestDataBuilder() {
		this.placa = "XYZ-789";
		this.tipoVehiculo = new TipoVehiculoEntityTestDataBuilder().build();
		this.cilindraje = 0;
	}
	
	public VehiculoEntityTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoEntityTestDataBuilder conTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public VehiculoEntityTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoEntity build() {
		return new VehiculoEntity(this.tipoVehiculo, this.placa, this.cilindraje);
	}
	
}
