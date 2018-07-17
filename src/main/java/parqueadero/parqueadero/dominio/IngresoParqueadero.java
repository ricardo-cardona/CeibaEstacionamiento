package parqueadero.parqueadero.dominio;

import java.util.Calendar;

public class IngresoParqueadero {
	
	private Vehiculo vehiculo;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private double valor;
	
	public IngresoParqueadero(Vehiculo vehiculo, Calendar fechaInicio, Calendar fechaFin, double valor) {
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

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
