package parqueadero.parqueadero.dominio;

public class TipoVehiculo {
	
	private Long id;
	private String nombre;
	private int capacidadMaxima;
	private double valorHora;
	private double valorDia;
	private boolean tieneCilindraje;
	private int altoCilindraje;
	private double valorAdicionalCilindraje;
	
	public TipoVehiculo(Long id, String nombre, int capacidadMaxima, double valorHora, double valorDia, boolean tieneCilindraje,
			int altoCilindraje, double valorAdicionalCilindraje) {
		this.id = id;
		this.nombre = nombre;
		this.capacidadMaxima = capacidadMaxima;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
		this.tieneCilindraje = tieneCilindraje;
		this.altoCilindraje = altoCilindraje;
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}
	
	public boolean getTieneCilindraje() {
		return tieneCilindraje;
	}
	
	public void setTieneCilindraje(boolean tieneCilindraje) {
		this.tieneCilindraje = tieneCilindraje;
	}

	public int getAltoCilindraje() {
		return altoCilindraje;
	}

	public void setAltoCilindraje(int altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
	}

	public double getValorAdicionalCilindraje() {
		return valorAdicionalCilindraje;
	}

	public void setValorAdicionalCilindraje(double valorAdicionalCilindraje) {
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
	}
	
}
