package parqueadero.parqueadero.persistencia.entidad;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehiculoEnParqueaderoEntity {
	
	@Id
	private String placa;
	private String tipo;
	private Calendar fechaIngreso;
	
	public VehiculoEnParqueaderoEntity() {
		super();
	}
	
	public VehiculoEnParqueaderoEntity(String placa, String tipo, Calendar fechaIngreso) {
		this.placa = placa;
		this.tipo = tipo;
		this.fechaIngreso = fechaIngreso;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}
