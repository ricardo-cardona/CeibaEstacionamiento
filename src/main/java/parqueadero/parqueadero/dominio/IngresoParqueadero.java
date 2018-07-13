package parqueadero.parqueadero.dominio;

import java.util.Date;

public class IngresoParqueadero {
	
	private Vehiculo vehiculo;
	private Date fechaInicio;
	private Date fechaFin;
	private double valor;
	
	public IngresoParqueadero(Vehiculo vehiculo, Date fechaInicio, Date fechaFin, double valor) {
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.valor = valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
