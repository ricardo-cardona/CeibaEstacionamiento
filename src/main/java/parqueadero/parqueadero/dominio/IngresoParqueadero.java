package parqueadero.parqueadero.dominio;

import java.util.Calendar;

public class IngresoParqueadero {
	
	Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public double getValor() {
		return valor;
	}
	
}
