package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.IngresoParqueadero;

public interface ReglaSalida {
	
	public void verificarRegla(IngresoParqueadero ingreso);
	
}
