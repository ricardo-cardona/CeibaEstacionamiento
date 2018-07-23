package parqueadero.parqueadero.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface VehiculoEnParqueaderoRepositorio extends JpaRepository<VehiculoEnParqueaderoEntity, Long> {
	
	@Query(nativeQuery = true
		, value = "SELECT I.ID_INGRESO_PARQUEADERO AS ID, V.PLACA AS PLACA, T.NOMBRE AS TIPO, I.FECHA_INICIO AS FECHA_INGRESO"
				+ " FROM INGRESO_PARQUEADERO I"
				+ " INNER JOIN VEHICULO V ON I.ID_VEHICULO = V.ID_VEHICULO"
				+ " INNER JOIN TIPO_VEHICULO T ON V.ID_TIPO_VEHICULO = T.ID_TIPO_VEHICULO"
				+ " WHERE I.FECHA_FIN IS NULL")
	public List<VehiculoEnParqueaderoEntity> vehiculosEnParqueadero();
	
	@Query(nativeQuery = true
		, value = "SELECT I.ID_INGRESO_PARQUEADERO AS ID, V.PLACA AS PLACA, T.NOMBRE AS TIPO, I.FECHA_INICIO AS FECHA_INGRESO"
				+ " FROM INGRESO_PARQUEADERO I"
				+ " INNER JOIN VEHICULO V ON I.ID_VEHICULO = V.ID_VEHICULO"
				+ " INNER JOIN TIPO_VEHICULO T ON V.ID_TIPO_VEHICULO = T.ID_TIPO_VEHICULO"
				+ " WHERE I.ID_INGRESO_PARQUEADERO = :ID_INGRESO_PARQUEADERO")
	public VehiculoEnParqueaderoEntity vehiculoEnParqueadero(@Param(value = "ID_INGRESO_PARQUEADERO") Long ingreso);
	
}
