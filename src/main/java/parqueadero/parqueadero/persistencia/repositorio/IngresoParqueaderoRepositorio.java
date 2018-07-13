package parqueadero.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;

@Repository
public interface IngresoParqueaderoRepositorio extends JpaRepository<IngresoParqueaderoEntity, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM INGRESOPARQUEADERO WHERE ID_VEHICULO = :ID_VEHICULO AND FECHA_FIN IS NULL")
	public IngresoParqueaderoEntity vehiculoEnParqueadero(@Param(value = "ID_VEHICULO") int vehiculo);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM VEHICULO V INNER JOIN INGRESOPARQUEADERO I ON V.ID_VEHICULO = I.ID_VEHICULO WHERE V.TIPO_VEHICULO = :TIPO_VEHICULO AND I.FECHA_FIN IS NULL")
	public int cantidadTipoVehiculoEnParqueadero(@Param(value = "TIPO_VEHICULO") String tipoVehiculo);
	
}
