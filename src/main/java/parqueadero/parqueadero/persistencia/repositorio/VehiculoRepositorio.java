package parqueadero.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

@Repository
public interface VehiculoRepositorio extends JpaRepository<VehiculoEntity, Long> {
	
	VehiculoEntity findByPlaca(String placa);
	
	@Query(nativeQuery = true, value = "SELECT V.* FROM VEHICULO V INNER JOIN INGRESOPARQUEADERO I ON V.ID_VEHICULO = I.ID_VEHICULO WHERE V.PLACA = :PLACA AND FECHA_FIN IS NULL")
	public VehiculoEntity vehiculoEstaEnParqueadero(@Param(value = "PLACA") String placa);
	
}
