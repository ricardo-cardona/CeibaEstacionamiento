package parqueadero.parqueadero.persistencia.entidad;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehiculoEnParqueaderoEntity {
	
	@Id
	private int id;
	private String placa;
	private String tipo;
	private Calendar fechaIngreso;
	
	public VehiculoEnParqueaderoEntity() {
		super();
	}
	
	public VehiculoEnParqueaderoEntity(int id, String placa, String tipo, Calendar fechaIngreso) {
		this.id = id;
		this.placa = placa;
		this.tipo = tipo;
		this.fechaIngreso = fechaIngreso;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
