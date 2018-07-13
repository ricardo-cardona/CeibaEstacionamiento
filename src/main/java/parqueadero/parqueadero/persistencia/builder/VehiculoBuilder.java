package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.Carro;
import parqueadero.parqueadero.dominio.Moto;
import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {
		
	}
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		
		Vehiculo vehiculo = null;
		
		if (vehiculoEntity.getTipoVehiculo().equalsIgnoreCase("carro")) {
			vehiculo = new Carro(vehiculoEntity.getPlaca());
		}
		
		if (vehiculoEntity.getTipoVehiculo().equalsIgnoreCase("moto")) {
			vehiculo = new Moto(vehiculoEntity.getPlaca(), vehiculoEntity.getCilindraje());
		}
		
		return vehiculo;
		
	}
	
}
