package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;

public final class IngresoParqueaderoBuilder {
	
	private IngresoParqueaderoBuilder() {
		
	}
	
	public static IngresoParqueadero convertirADominio(IngresoParqueaderoEntity ingresoEntity) {
		
		if (ingresoEntity == null) {
			return null;
		}
		
		IngresoParqueadero ingreso = new IngresoParqueadero(
			  VehiculoBuilder.convertirADominio(ingresoEntity.getVehiculo())
			, ingresoEntity.getFechaInicio()
			, ingresoEntity.getFechaFin()
			, ingresoEntity.getValor()
		);
		
		ingreso.setId(ingresoEntity.getId());
		
		return ingreso;
		
	}
	
	public static IngresoParqueaderoEntity convertirAEntity(IngresoParqueadero ingreso) {
		
		if (ingreso == null) {
			return null;
		}
		
		IngresoParqueaderoEntity ingresoEntity = new IngresoParqueaderoEntity(
			  VehiculoBuilder.convertirAEntity(ingreso.getVehiculo())
			, ingreso.getFechaInicio()
			, ingreso.getFechaFin()
			, ingreso.getValor()
		);
		
		ingresoEntity.setId(ingreso.getId());
		
		return ingresoEntity;
		
	}

}
