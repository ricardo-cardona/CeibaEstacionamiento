package parqueadero.parqueadero.excepcion;

public class IngresoParqueaderoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IngresoParqueaderoExcepcion(String message) {
		super(message);
	}
}
