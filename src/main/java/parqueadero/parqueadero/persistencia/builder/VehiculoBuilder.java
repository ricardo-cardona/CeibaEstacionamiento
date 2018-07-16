package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {
		
	}
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculo) {
		
		if (vehiculo == null) {
			return null;
		}
		
		return new Vehiculo(
			vehiculo.getPlaca()
			, TipoVehiculoBuilder.convertirADominio(vehiculo.getTipoVehiculo())
			, vehiculo.getCilindraje()
		);
		
	}
	
}
