package parqueadero.parqueadero.persistencia.builder;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;

public class IngresoParqueaderoBuilder {
	
	private IngresoParqueaderoBuilder() {
		
	}
	
	public static IngresoParqueadero convertirADominio(IngresoParqueaderoEntity ingreso) {
		
		if (ingreso == null) {
			return null;
		}
		
		return new IngresoParqueadero(
			VehiculoBuilder.convertirADominio(ingreso.getVehiculo())
			, ingreso.getFechaInicio()
			, ingreso.getFechaFin()
			, ingreso.getValor()
		);
		
	}
	
	public static IngresoParqueaderoEntity convertirAEntity(IngresoParqueadero ingreso) {
		
		if (ingreso == null) {
			return null;
		}
		
		return new IngresoParqueaderoEntity(
			  VehiculoBuilder.convertirAEntity(ingreso.getVehiculo())
			, ingreso.getFechaInicio()
			, ingreso.getFechaFin()
			, ingreso.getValor()
		);
		
	}

}
