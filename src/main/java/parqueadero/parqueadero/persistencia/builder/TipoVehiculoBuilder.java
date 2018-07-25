package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.TipoVehiculo;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

public final class TipoVehiculoBuilder {
	
	private TipoVehiculoBuilder() {
		
	}
	
	public static TipoVehiculo convertirADominio(TipoVehiculoEntity tipoVehiculoEntity) {
		
		if (tipoVehiculoEntity == null) {
			return null;
		}
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo(
				  tipoVehiculoEntity.getNombre()
				, tipoVehiculoEntity.getCapacidadMaxima()
				, tipoVehiculoEntity.getValorHora()
				, tipoVehiculoEntity.getValorDia()
				, tipoVehiculoEntity.getTieneCilindraje()
				, tipoVehiculoEntity.getAltoCilindraje()
				, tipoVehiculoEntity.getValorAdicionalCilindraje()
		);
		
		tipoVehiculo.setId(tipoVehiculoEntity.getId());
		
		return tipoVehiculo;
		
	}
	
	public static TipoVehiculoEntity convertirAEntity(TipoVehiculo tipoVehiculo) {
		
		if (tipoVehiculo == null) {
			return null;
		}
		
		TipoVehiculoEntity tipoVehiculoEntity = new TipoVehiculoEntity(
				  tipoVehiculo.getNombre()
				, tipoVehiculo.getCapacidadMaxima()
				, tipoVehiculo.getValorHora()
				, tipoVehiculo.getValorDia()
				, tipoVehiculo.getTieneCilindraje()
				, tipoVehiculo.getAltoCilindraje()
				, tipoVehiculo.getValorAdicionalCilindraje()
		);
		
		tipoVehiculoEntity.setId(tipoVehiculo.getId());
		
		return tipoVehiculoEntity;
		
	}
	
}
