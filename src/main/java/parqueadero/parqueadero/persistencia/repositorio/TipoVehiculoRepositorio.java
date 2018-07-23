package parqueadero.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculoEntity, Long> {
	
}
