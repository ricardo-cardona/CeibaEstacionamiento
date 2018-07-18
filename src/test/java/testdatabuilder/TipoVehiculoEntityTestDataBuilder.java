package testdatabuilder;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

public class TipoVehiculoEntityTestDataBuilder {
	
	private Long id;
	private String nombre;
	private int capacidadMaxima;
	private double valorHora;
	private double valorDia;
	private boolean tieneCilindraje;
	private int altoCilindraje;
	private double valorAdicionalCilindraje;
	
	public TipoVehiculoEntityTestDataBuilder() {
		this.id = 1L;
		this.nombre = "CARRO";
		this.capacidadMaxima = 20;
		this.valorHora = 1000;
		this.valorDia = 8000;
		this.tieneCilindraje = false;
		this.altoCilindraje = 0;
		valorAdicionalCilindraje = 0;
	}
	
	public TipoVehiculoEntityTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conValorHora(double valorHora) {
		this.valorHora = valorHora;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conValorDia(double valorDia) {
		this.valorDia = valorDia;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conTieneCilindraje(boolean tieneCilindraje) {
		this.tieneCilindraje = tieneCilindraje;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conAltoCilindraje(int altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
		return this;
	}
	
	public TipoVehiculoEntityTestDataBuilder conValorAdicionalCilindraje(double valorAdicionalCilindraje) {
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
		return this;
	}
	
	public TipoVehiculoEntity build() {
		
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntity(this.nombre, this.capacidadMaxima, this.valorHora, this.valorDia
			, this.tieneCilindraje, this.altoCilindraje, this.valorAdicionalCilindraje);
		
		tipoVehiculo.setId(this.id);
		
		return tipoVehiculo;
		
	}
	
}
