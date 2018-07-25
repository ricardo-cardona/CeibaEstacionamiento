package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.servicio.VehiculoServicio;

public class ReglaVehiculoEnParqueadero implements ReglaIngreso {
	
	public static final String VEHICULO_YA_ESTA_EN_PARQUEADERO = "El vehículo ya se encuentra en el parqueadero.";
	
	private VehiculoServicio vehiculoServicio;
	
	public ReglaVehiculoEnParqueadero(VehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {
		
		vehiculo = VehiculoBuilder.convertirADominio(vehiculoServicio.vehiculoEstaEnParqueadero(vehiculo.getPlaca()));
		
		if (vehiculo != null) {
			mostrarMensaje(VEHICULO_YA_ESTA_EN_PARQUEADERO);
		}
		
	}
	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoParqueaderoExcepcion(mensaje);
	}

}
