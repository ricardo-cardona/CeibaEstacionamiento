package parqueadero.parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoVehiculo")
public class TipoVehiculoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_VEHICULO", nullable = false)
	private Long id;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "CAPACIDAD_MAXIMA", nullable = false)
	private int capacidadMaxima;
	
	@Column(name = "VALOR_HORA", nullable = false)
	private double valorHora;
	
	@Column(name = "VALOR_DIA", nullable = false)
	private double valorDia;
	
	@Column(name = "TIENE_CILINDRAJE", nullable = false)
	private boolean tieneCilindraje;
	
	@Column(name = "ALTO_CILINDRAJE", nullable = false)
	private int altoCilindraje;
	
	@Column(name = "VALOR_ADICIONAL_CILINDRAJE", nullable = false)
	private double valorAdicionalCilindraje;
	
	public TipoVehiculoEntity() {
		super();
	}
	
	public TipoVehiculoEntity(Long id) {
		this.id = id;
	}
	
	public TipoVehiculoEntity(Long id, String nombre, int capacidadMaxima, double valorHora, double valorDia, boolean tieneCilindraje, 
			int altoCilindraje, double valorAdicionalCilindraje) {
		this.id = id;
		this.nombre = nombre;
		this.capacidadMaxima = capacidadMaxima;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
		this.tieneCilindraje = tieneCilindraje;
		this.altoCilindraje = altoCilindraje;
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}
	
	public boolean getTieneCilindraje() {
		return tieneCilindraje;
	}
	
	public void setTieneCilindraje(boolean tieneCilindraje) {
		this.tieneCilindraje = tieneCilindraje;
	}

	public int getAltoCilindraje() {
		return altoCilindraje;
	}

	public void setAltoCilindraje(int altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
	}

	public double getValorAdicionalCilindraje() {
		return valorAdicionalCilindraje;
	}

	public void setValorAdicionalCilindraje(double valorAdicionalCilindraje) {
		this.valorAdicionalCilindraje = valorAdicionalCilindraje;
	}
	
}
