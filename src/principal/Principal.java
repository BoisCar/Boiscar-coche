package principal;

import pantallas.climatizador.Climatizador;
import principal.ventana.Ventana;
import sensores.ListaSensores;
import sensores.Sensor;
import sensores.lineaSerie.LineaSerie;
import voz.habla.Habla;
import voz.habla.Voz;
import voz.reconocimiento.Reconocedor;

public class Principal {
	static ListaSensores sensores;
	static LineaSerie lineaSerie;
	
	public Principal() {
		Thread inicio = new Thread(Ventana.iniciar());
        inicio.start();
		
		inicializarModelos();
		Reconocedor.iniciarRec();
		Voz.init("vozCoche");
		
		try {
			lineaSerie = new LineaSerie();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Climatizador.actualizarTemperatura();
	}
	
	private void inicializarModelos() {
		Climatizador.iniciar();
		sensores = new ListaSensores();
		inicializar(sensores);
		sensores.actualizarListaSensores();
	}
	
	public void inicializar(ListaSensores sensores) {
		sensores.add(new Sensor("Rueda delantera derecha", "rueda",true, 1));
		sensores.add(new Sensor("Rueda delantera izquierda", "rueda", true, 2));
		sensores.add(new Sensor("Rueda trasera derecha", "rueda", true, 3));
		sensores.add(new Sensor("Rueda trasera izquierda", "rueda", true, 4));
		
		sensores.add(new Sensor("Luz delantera derecha", "luz", true, 5));
		sensores.add(new Sensor("Luz delantera izquierda", "luz", true, 6));
		sensores.add(new Sensor("Luz trasera derecha", "luz", true, 7));
		sensores.add(new Sensor("Luz trasera izquierda", "luz", true, 8));
		
		sensores.add(new Sensor("Nivel de combustible", "combustible", true, 9));
		sensores.add(new Sensor("Estado de los frenos", "freno", true, 10));
		sensores.add(new Sensor("Estado de los airbags", "airbag", true, 11));
	}
	
	public static ListaSensores getSensores() {
		return sensores;
	}
	
	public static LineaSerie getLineaSerie() {
		return lineaSerie;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Principal programa = new Principal();
	}

}
