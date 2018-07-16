package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

public class TipoVehiculoBuilder {
	
	private TipoVehiculoBuilder() {
		
	}
	
	public static TipoVehiculo convertirADominio(TipoVehiculoEntity tipoVehiculo) {
		
		if (tipoVehiculo == null) {
			return null;
		}
		
		return new TipoVehiculo(
				tipoVehiculo.getNombre()
				, tipoVehiculo.getCapacidadMaxima()
				, tipoVehiculo.getValorHora()
				, tipoVehiculo.getValorDia()
				, tipoVehiculo.getTieneCilindraje()
				, tipoVehiculo.getAltoCilindraje()
				, tipoVehiculo.getValorAdicionalCilindraje()
		);
		
	}
	
}
