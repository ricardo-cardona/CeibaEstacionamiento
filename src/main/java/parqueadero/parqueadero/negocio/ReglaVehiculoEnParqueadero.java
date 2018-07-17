package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.servicio.VehiculoServicio;

public class ReglaVehiculoEnParqueadero implements ReglaNegocio {
	
	private static final String VEHICULO_YA_EN_PARQUEADERO = "El vehículo ya se encuentra en el parqueadero.";
	private VehiculoServicio vehiculoServicio;
	
	public ReglaVehiculoEnParqueadero(VehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}
	
	public boolean verificarRegla(Vehiculo vehiculo) {
		
		vehiculo = VehiculoBuilder.convertirADominio(vehiculoServicio.vehiculoEstaEnParqueadero(vehiculo.getPlaca()));
		
		return (vehiculo == null);
		
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(VEHICULO_YA_EN_PARQUEADERO);
	}

}
