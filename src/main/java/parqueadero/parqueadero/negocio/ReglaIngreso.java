package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;

public interface ReglaIngreso {
	
	public void verificarRegla(Vehiculo vehiculo);
	
	public void mostrarMensaje(String mensaje);
	
}
