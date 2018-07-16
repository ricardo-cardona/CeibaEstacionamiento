package parqueadero.parqueadero.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;

@Repository
public interface IngresoParqueaderoRepositorio extends JpaRepository<IngresoParqueaderoEntity, Long> {
	
	@Query(nativeQuery = true
		, value = "SELECT *"
				+ " FROM INGRESO_PARQUEADERO"
				+ " WHERE ID_VEHICULO = :ID_VEHICULO"
				+ " AND FECHA_FIN IS NULL")
	public IngresoParqueaderoEntity vehiculoEnParqueadero(@Param(value = "ID_VEHICULO") int vehiculo);
	
	@Query(nativeQuery = true
		, value = "SELECT COUNT(1)"
				+ " FROM TIPO_VEHICULO T"
				+ " INNER JOIN VEHICULO V ON T.ID_TIPO_VEHICULO = V.ID_TIPO_VEHICULO"
				+ " INNER JOIN INGRESO_PARQUEADERO I ON V.ID_VEHICULO = I.ID_VEHICULO"
				+ " WHERE T.ID_TIPO_VEHICULO = :ID_TIPO_VEHICULO"
				+ " AND I.FECHA_FIN IS NULL")
	public int cantidadTipoVehiculoEnParqueadero(@Param(value = "ID_TIPO_VEHICULO") Long tipoVehiculo);
	
	@Query(nativeQuery = true
		, value = "SELECT V.PLACA AS PLACA, T.NOMBRE AS TIPO, I.FECHA_INICIO AS FECHA_INGRESO"
				+ " FROM INGRESO_PARQUEADERO I"
				+ " INNER JOIN VEHICULO V ON I.ID_VEHICULO = V.ID_VEHICULO"
				+ " INNER JOIN TIPO_VEHICULO T ON V.ID_TIPO_VEHICULO = T.ID_TIPO_VEHICULO"
				+ " WHERE I.FECHA_FIN IS NULL")
	public List<VehiculoEnParqueaderoEntity> vehiculosEnParqueadero();
	
}
