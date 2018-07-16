package parqueadero.parqueadero.excepcion;

public class SalidaParqueaderoExcepcion extends RuntimeException {
	
	private static final long serialVersionUID = 2L;
	
	public SalidaParqueaderoExcepcion(String message) {
		super(message);
	}
	
}
