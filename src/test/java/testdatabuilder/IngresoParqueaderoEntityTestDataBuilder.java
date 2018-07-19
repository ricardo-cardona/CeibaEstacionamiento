package testdatabuilder;

import java.util.Calendar;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

public class IngresoParqueaderoEntityTestDataBuilder {
	
	private Long id;
	private VehiculoEntity vehiculo;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private double valor;
	
	public IngresoParqueaderoEntityTestDataBuilder() {
		this.id = 1L;
		this.vehiculo = new VehiculoEntityTestDataBuilder().build();
		this.fechaInicio = Calendar.getInstance();
		this.fechaFin = null;
		this.valor = 0;
	}
	
	public IngresoParqueaderoEntityTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public IngresoParqueaderoEntityTestDataBuilder conVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public IngresoParqueaderoEntityTestDataBuilder conFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
		return this;
	}
	
	public IngresoParqueaderoEntityTestDataBuilder conFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
		return this;
	}
	
	public IngresoParqueaderoEntityTestDataBuilder conValor(double valor) {
		this.valor = valor;
		return this;
	}
	
	public IngresoParqueaderoEntity build() {
		
		IngresoParqueaderoEntity ingreso = new IngresoParqueaderoEntity(this.vehiculo, this.fechaInicio, this.fechaFin, this.valor);
		ingreso.setId(this.id);
		
		return ingreso;
		
	}
	
}
