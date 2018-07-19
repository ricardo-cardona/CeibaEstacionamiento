package testdatabuilder;

import java.util.Calendar;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.dominio.Vehiculo;

public class IngresoParqueaderoTestDataBuilder {
	
	private Vehiculo vehiculo;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private double valor;
	
	public IngresoParqueaderoTestDataBuilder() {
		this.vehiculo = new VehiculoTestDataBuilder().build();
		this.fechaInicio = Calendar.getInstance();
		this.fechaFin = null;
		this.valor = 0;
	}
	
	public IngresoParqueaderoTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public IngresoParqueaderoTestDataBuilder conFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
		return this;
	}
	
	public IngresoParqueaderoTestDataBuilder conFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
		return this;
	}
	
	public IngresoParqueaderoTestDataBuilder conValor(double valor) {
		this.valor = valor;
		return this;
	}
	
	public IngresoParqueadero build() {
		
		return new IngresoParqueadero(this.vehiculo, this.fechaInicio, this.fechaFin, this.valor);
		
	}
	
}
