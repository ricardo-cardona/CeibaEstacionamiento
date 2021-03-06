package parqueadero.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface VehiculoRepositorio extends JpaRepository<VehiculoEntity, Long> {
	
	public VehiculoEntity findByPlaca(String placa);
	
	@Query(nativeQuery = true, value = "SELECT V.* FROM VEHICULO V INNER JOIN INGRESO_PARQUEADERO I ON V.ID_VEHICULO = I.ID_VEHICULO WHERE V.PLACA = :PLACA AND FECHA_FIN IS NULL")
	public VehiculoEntity vehiculoEstaEnParqueadero(@Param(value = "PLACA") String placa);
	
}
