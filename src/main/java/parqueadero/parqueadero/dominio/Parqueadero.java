package parqueadero.parqueadero.dominio;

public class Parqueadero {
	
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 4000;
	public static final int ALTO_CILINDRAJE_MOTO = 500;
	public static final int VALOR_ADICIONAL_ALTO_CILINDRAJE = 2000;
	public static final int CANTIDAD_MAXIMA_COBRO_HORA = 9;
	public static final int CANTIDAD_MAXIMA_COBRO_DIA = 24;
	
	private static int carrosEnParqueadero = 0;
	private static int motosEnParqueadero = 0;
	
	private Parqueadero() {
		
	}
	
	public static int getCarrosEnParqueadero() {
		return carrosEnParqueadero;
	}
	
	public static int getMotosEnParqueadero() {
		return motosEnParqueadero;
	}
	
	public static void agregarCarrosAParqueadero(int carros) {
		carrosEnParqueadero += carros;
	}
	
	public static void agregarMotosAParqueadero(int motos) {
		motosEnParqueadero += motos;
	}
	
}
