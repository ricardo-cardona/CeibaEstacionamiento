package parqueadero.parqueadero.dominio;

public class Vehiculo {
	
	private String placa;
	private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	
	public Vehiculo(String placa, TipoVehiculo tipoVehiculo, int cilindraje) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
	}

	public String getPlaca() {
		return placa;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public int getCilindraje() {
		return cilindraje;
	}
	
}
