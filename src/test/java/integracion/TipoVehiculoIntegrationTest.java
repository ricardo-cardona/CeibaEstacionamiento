package integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.ParqueaderoApplication;
import parqueadero.parqueadero.controlador.TipoVehiculoControlador;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

@SpringBootTest(classes = ParqueaderoApplication.class)
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value=true)
public class TipoVehiculoIntegrationTest {
	
	@Autowired
    private TipoVehiculoControlador tipoVehiculoControlador;
	
	private static final int CANTIDAD_TIPOS_DE_VEHICULO = 2; //carro y moto

	@Test
	public void consultarTiposDeVehiculoTest() {
		
		//arrange
		List<TipoVehiculoEntity> tipos = null;
		
		//act
		tipos = tipoVehiculoControlador.consultarTiposDeVehiculo();
		
		//assert
		assertNotNull(tipos);
		assertEquals(CANTIDAD_TIPOS_DE_VEHICULO, tipos.size());
	}

}
