package testdatabuilder;

import java.util.Calendar;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.dominio.Vehiculo;

public class IngresoParqueaderoTestDataBuilder {
	
	Long id;
	private Vehiculo vehiculo;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private double valor;
	
	public IngresoParqueaderoTestDataBuilder() {
		this.id = 1L;
		this.vehiculo = new VehiculoTestDataBuilder().build();
		this.fechaInicio = Calendar.getInstance();
		this.fechaFin = null;
		this.valor = 0;
	}
	
	public IngresoParqueaderoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
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
		
		IngresoParqueadero ingreso = new IngresoParqueadero(this.vehiculo, this.fechaInicio, this.fechaFin, this.valor);
		ingreso.setId(this.id);
		
		return ingreso;
		
	}
	
}
