package parqueadero.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

@Repository
public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculoEntity, Long> {
	
}
