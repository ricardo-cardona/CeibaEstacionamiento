package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;

public interface ReglaNegocio {
	
	public boolean verificarRegla(Vehiculo vehiculo);
	
	public void mostrarMensaje();
	
}
