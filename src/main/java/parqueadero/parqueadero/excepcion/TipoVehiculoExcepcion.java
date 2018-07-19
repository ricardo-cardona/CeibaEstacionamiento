package parqueadero.parqueadero.excepcion;

public class TipoVehiculoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TipoVehiculoExcepcion(String message) {
		super(message);
	}
}
