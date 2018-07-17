package testdatabuilder;

import parqueadero.parqueadero.dominio.TipoVehiculo;

public class TipoVehiculoTestDataBuilder {
	
	private Long id;
	private String nombre;
	private int capacidadMaxima;
	private double valorHora;
	private double valorDia;
	private boolean tieneCilindraje;
	private int altoCilindraje;
	private double valorAdicionalCilindraje;
	
	public TipoVehiculoTestDataBuilder() {
		this.id = 1L;
		this.nombre = "CARRO";
		this.capacidadMaxima = 20;
		this.valorHora = 1000;
		this.valorDia = 8000;
		this.tieneCilindraje = false;
		this.altoCilindraje = 0;
		valorAdicionalCilindraje = 0;
	}
	
	public TipoVehiculoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conValorHora(double valorHora) {
		this.valorHora = valorHora;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conValorDia(double valorDia) {
		this.valorDia = valorDia;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conTieneCilindraje(boolean tieneCilindraje) {
		this.tieneCilindraje = tieneCilindraje;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conAltoCilindraje(int altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conValorAdicionalCilindraje(double valorAdicionalCilindraje) {
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
		return this;
	}
	
	public TipoVehiculo build() {
		return new TipoVehiculo(this.id, this.nombre, this.capacidadMaxima, this.valorHora, this.valorDia
			, this.tieneCilindraje, this.altoCilindraje, this.valorAdicionalCilindraje);
	}
	
}
